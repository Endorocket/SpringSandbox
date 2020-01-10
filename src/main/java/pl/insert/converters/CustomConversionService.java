package pl.insert.converters;

import org.springframework.binding.convert.converters.ObjectToCollection;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.stereotype.Component;

@Component
public class CustomConversionService extends DefaultConversionService {

  @Override
  protected void addDefaultConverters() {
    super.addDefaultConverters();
    addConverter(new ObjectToCollection(this));
  }
}
