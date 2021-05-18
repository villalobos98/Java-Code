package com.mindex.challenge.data;


public class ReportingStructure extends Employee{
    private Integer numberOfReports;

    public Integer getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(Employee e) {
        int count = findDistinct(e);
        this.numberOfReports = count;
    }

    private int findDistinct(Employee currentEmployee) {
        int distinctEmployees = 0;
        for( int i = 0; i< currentEmployee.getDirectReports().size(); i++){
            if (currentEmployee.getDirectReports().size() == 0){
                break;
            }
            distinctEmployees += 1;
            findDistinct(currentEmployee.getDirectReports().get(i));
        }

        return distinctEmployees;
    }
}
