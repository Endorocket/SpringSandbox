package pl.insert.webflow.config;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.mvc.builder.DelegatingFlowViewResolver;
import org.springframework.webflow.mvc.builder.FlowResourceFlowViewResolver;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.view.AbstractMvcViewFactory;
import org.springframework.webflow.mvc.view.FlowViewResolver;
import org.springframework.webflow.validation.WebFlowMessageCodesResolver;

import java.util.List;

/**
 * Class created to enable complex bindings in class EnhancedServletMvcView
 */
@Component
public class EnhancedMvcViewFactoryCreator extends MvcViewFactoryCreator {

  private FlowViewResolver flowViewResolver = new FlowResourceFlowViewResolver();
  private MessageCodesResolver messageCodesResolver = new WebFlowMessageCodesResolver();

  @Override
  protected AbstractMvcViewFactory createMvcViewFactory(Expression viewId, ExpressionParser expressionParser, ConversionService conversionService, BinderConfiguration binderConfiguration) {

    return new EnhancedServletMvcViewFactory(viewId, flowViewResolver, expressionParser, conversionService, binderConfiguration, messageCodesResolver);
  }

  @Override
  public void setFlowViewResolver(FlowViewResolver flowViewResolver) {
    super.setFlowViewResolver(flowViewResolver);
    this.flowViewResolver = flowViewResolver;
  }

  @Override
  public void setViewResolvers(List<ViewResolver> viewResolvers) {
    super.setViewResolvers(viewResolvers);
    this.flowViewResolver = new DelegatingFlowViewResolver(viewResolvers);
  }

  @Override
  public void setMessageCodesResolver(MessageCodesResolver messageCodesResolver) {
    super.setMessageCodesResolver(messageCodesResolver);
    this.messageCodesResolver = messageCodesResolver;
  }
}
