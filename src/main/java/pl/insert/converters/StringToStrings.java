package pl.insert.converters;

import org.springframework.binding.convert.converters.StringToObject;

public class StringToStrings extends StringToObject {

  public StringToStrings(Class<?> objectClass) {
    super(objectClass);
  }

  @Override
  protected Object toObject(String s, Class<?> aClass) throws Exception {
    return null;
  }

  @Override
  protected String toString(Object o) throws Exception {
    return null;
  }
}
