package pl.insert.webflow.config;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.View;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.ServletMvcViewFactory;
import org.springframework.webflow.mvc.view.AbstractMvcView;
import org.springframework.webflow.mvc.view.FlowViewResolver;

public class EnhancedServletMvcViewFactory extends ServletMvcViewFactory {

  public EnhancedServletMvcViewFactory(Expression viewId, FlowViewResolver viewResolver, ExpressionParser expressionParser, ConversionService conversionService, BinderConfiguration binderConfiguration, MessageCodesResolver messageCodesResolver) {
    super(viewId, viewResolver, expressionParser, conversionService, binderConfiguration, messageCodesResolver);
  }

  @Override
  protected AbstractMvcView createMvcView(View view, RequestContext context) {
    return new EnhancedServletMvcView(view, context);
  }
}
