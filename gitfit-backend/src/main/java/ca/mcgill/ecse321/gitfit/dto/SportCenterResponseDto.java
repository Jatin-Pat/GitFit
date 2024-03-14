package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Time;

import ca.mcgill.ecse321.gitfit.model.SportCenter;

/**
 * DTO class for SportCenter
 * @author William Wang (wangwiza)
 */
public class SportCenterResponseDto {
    
    private String name;
    private int maxCapacity;
    private Time openingTime;
    private Time closingTime;

    public SportCenterResponseDto(SportCenter model) {
        this.name = model.getName();
        this.maxCapacity = model.getMaxCapacity();
        this.openingTime = model.getOpeningTime();
        this.closingTime = model.getClosingTime();
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }

}
