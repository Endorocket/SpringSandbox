package pl.insert.webflow.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.binding.convert.ConversionExecutor;
import org.springframework.binding.expression.EvaluationException;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ParserContext;
import org.springframework.binding.expression.support.FluentParserContext;
import org.springframework.binding.mapping.impl.DefaultMapper;
import org.springframework.binding.mapping.impl.DefaultMapping;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.View;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.engine.builder.BinderConfiguration.Binding;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.ServletMvcView;
import org.springframework.webflow.mvc.view.AbstractMvcView;

import java.util.Set;

/**
 * In addition to standard binding in flow, class uses AntPathMatcher to bind collection parameters
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html
 *
 * e.g.
 * <binding property="optionalModules[*].durationId"/>
 *
 */
public class EnhancedServletMvcView extends ServletMvcView {

  private static final Log logger = LogFactory.getLog(AbstractMvcView.class);

  public EnhancedServletMvcView(View view, RequestContext context) {
    super(view, context);
  }

  protected void addModelBindings(DefaultMapper mapper, Set parameterNames, Object model) {
    for (Binding binding : getBinderConfiguration().getBindings()) {
      String parameterName = binding.getProperty();
      if (parameterNames.contains(parameterName)) {
        addMapping(mapper, binding, model);
      } else {
        boolean matched = false;
        if (parameterName.contains("*")) {
          PathMatcher pathMatcher = new AntPathMatcher();
          for (Object name : parameterNames) {
            String pName = (String) name;
            if (pathMatcher.match(parameterName, pName)) {
              addMapping(mapper, binding, model, pName);
              matched = true;
            }
          }
        }
        if (!matched && getFieldMarkerPrefix() != null && parameterNames.contains(getFieldMarkerPrefix() + parameterName)) {
          addEmptyValueMapping(mapper, parameterName, model);
        }
      }
    }
  }

  protected void addMapping(DefaultMapper mapper, Binding binding, Object model) {
    addMapping(mapper, binding, model, null);
  }

  private void addMapping(DefaultMapper mapper, Binding binding, Object model, String property) {
    if (null == property) {
      property = binding.getProperty();
    }
    Expression source = new RequestParameterExpression(property);
    ParserContext parserContext = new FluentParserContext().evaluate(model.getClass());
    Expression target = getExpressionParser().parseExpression(property, parserContext);
    DefaultMapping mapping = new DefaultMapping(source, target);
    mapping.setRequired(binding.getRequired());
    if (binding.getConverter() != null) {
      Assert.notNull(getConversionService(),
          "A ConversionService must be configured to use resolve custom converters to use during binding");
      ConversionExecutor conversionExecutor = getConversionService().getConversionExecutor(binding.getConverter(),
          String.class, target.getValueType(model));
      mapping.setTypeConverter(conversionExecutor);
    }
    if (logger.isDebugEnabled()) {
      logger.debug("Adding mapping for parameter '" + property + "'");
    }
    mapper.addMapping(mapping);
  }

  private static class RequestParameterExpression implements Expression {

    private String parameterName;

    public RequestParameterExpression(String parameterName) {
      this.parameterName = parameterName;
    }

    public String getExpressionString() {
      return parameterName;
    }

    public Object getValue(Object context) throws EvaluationException {
      ParameterMap parameters = (ParameterMap) context;
      return parameters.asMap().get(parameterName);
    }

    public Class<?> getValueType(Object context) {
      return String.class;
    }

    public void setValue(Object context, Object value) throws EvaluationException {
      throw new UnsupportedOperationException("Setting request parameters is not allowed");
    }

    public String toString() {
      return "parameter:'" + parameterName + "'";
    }
  }

}
