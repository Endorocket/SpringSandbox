package pl.insert.dto;

import java.io.Serializable;

public class SurveyDto implements Serializable {

  private int computers;
  private String companyName;

  public SurveyDto(int computers, String companyName) {
    this.computers = computers;
    this.companyName = companyName;
  }

  public int getComputers() {
    return computers;
  }

  public void setComputers(int computers) {
    this.computers = computers;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return String.format("SurveyDto [        computers=%s        ,         companyName=%s]", computers, companyName);
  }
}
