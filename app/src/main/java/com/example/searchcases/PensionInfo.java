package com.example.searchcases;

import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Содержит всю информацию о пенсионере
public class PensionInfo {


    private String RegistryName;
    private String NumberInBase;
    private String NumberRegistry;
    private String LastName;
    private String Name;
    private String FartherName;
    private String DataStart;
    private String DataEnd;
    private String PageCount;
    private String Remark;

    public PensionInfo(){}
    public PensionInfo(PensionInfo person) {
        this.RegistryName = person.RegistryName;
        this.NumberInBase = person.NumberInBase;
        this.NumberRegistry = person.NumberRegistry;
        this.LastName = person.LastName;
        this.Name = person.Name;
        this.FartherName = person.FartherName;
        this.DataStart = person.DataStart;
        this.DataEnd = person.DataEnd;
        this.PageCount = person.PageCount;
        this.Remark = person.Remark;
    }


    public String getRegistryName() {
        return RegistryName;
    }

    public void setRegistryName(String registryName) {
        RegistryName = registryName;
    }

    public String getNumberInBase() {
        return NumberInBase;
    }

    public void setNumberInBase(String numberInBase) {
        NumberInBase = numberInBase;
    }

    public String getNumberRegistry() {
        return NumberRegistry;
    }

    public void setNumberRegistry(String numberRegistry) {
        NumberRegistry = numberRegistry;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFartherName() {
        return FartherName;
    }

    public void setFartherName(String fartherName) {
        FartherName = fartherName;
    }

    public String getDataStart() {
        return DataStart;
    }

    public void setDataStart(String dataStart) {
        DataStart = dataStart;
    }

    public String getDataEnd() {
        return DataEnd;
    }

    public void setDataEnd(String dataEnd) {
        DataEnd = dataEnd;
    }

    public String getPageCount() {
        return PageCount;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
