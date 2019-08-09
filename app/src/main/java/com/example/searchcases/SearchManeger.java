package com.example.searchcases;

import android.app.Activity;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchManeger {
    private PensionInfo person;
    private FindInRegistr finder;
    private List<PensionInfo> allPensList = null;

    private int[] xmlIDs = {
            R.xml.export_2012,
            R.xml.export_2013,
            R.xml.export_2014,
            R.xml.export_2015,
            R.xml.export_2016,
            R.xml.export_2017,
            R.xml.export_2018,
    };

    public SearchManeger() {
        init();
    }

    public SearchManeger(Activity view) {
        init();
        parseAndAddXmlResource(view);
    }

    public List<PensionInfo> parseRegistry(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<PensionInfo> personList = null;

        personList = fillOutPensionerList(parser);

        return personList;
    }

    private void init() {
        person = new PensionInfo();
        finder = new FindInRegistr();
    }

    private void parseAndAddXmlResource(Activity view) {
        try {
            List<XmlPullParser> parsers = new ArrayList<>(xmlIDs.length);

            for (int i : xmlIDs) {
                parsers.add(view.getResources().getXml(i));
            }
            allPensList = new ArrayList<PensionInfo>(15000);
            for (XmlPullParser parser : parsers) {
                allPensList.addAll(parseRegistry(parser));
            }
        } catch (Throwable t) {
            Toast.makeText(view,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }


    private List<PensionInfo> fillOutPensionerList(XmlPullParser parser) throws XmlPullParserException, IOException {
        PensionInfo person = new PensionInfo();
        List<PensionInfo> personList = new ArrayList<>();
        String registryName = "";

        final String PERSON_TAG = "person";
        final String FULL_NAME_TAG = "fullName";
        final String REGISTER_TAG = "Register";

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            int eventType = parser.getEventType();
            // начало тэга
            if (eventType == XmlPullParser.START_TAG) {
                String nameTag = parser.getName();
                switch (nameTag) {
                    case PERSON_TAG:
                        person = parsePerson(parser, person);
                        break;
                    case FULL_NAME_TAG:
                        person = parseFullName(parser, person);
                        break;
                    case REGISTER_TAG:
                        registryName = parseRegistryName(parser);
                        break;
                }
            }
            if (eventType == XmlPullParser.END_TAG && parser.getName().equals(PERSON_TAG)) {
                if (person != null)
                    person.setRegistryName(registryName);
                personList.add(person);
            }
            parser.next();
        }
        return personList;
    }


    private String parseRegistryName(XmlPullParser parser) {
        if (parser.getAttributeCount() == 0) {
            return null;
        }
        return parser.getAttributeValue(0);
    }

    private PensionInfo parsePerson(XmlPullParser parser, PensionInfo personOrig) {
        PensionInfo person = new PensionInfo(personOrig);

        final String NUMBER_IN_BASE = "numberInBase";
        final String NUMBER_REGISTR = "numberRegistr";
        final String DATA_START = "dataStart";
        final String DATA_END = "dataEnd";
        final String PAGE_COUNT = "pageCount";
        final String REMARK = "remark";


        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String attributeName = parser.getAttributeName(i);
            String attributeValue = parser.getAttributeValue(i);

            switch (attributeName) {
                case NUMBER_IN_BASE:
                    person.setNumberInBase(attributeValue);
                    break;
                case NUMBER_REGISTR:
                    person.setNumberRegistry(attributeValue);
                    break;
                case DATA_START:
                    person.setDataStart(attributeValue);
                    break;
                case DATA_END:
                    person.setDataEnd(attributeValue);
                    break;
                case PAGE_COUNT:
                    person.setPageCount(attributeValue);
                    break;
                case REMARK:
                    person.setRemark(attributeValue);
                    break;
            }
        }
        return person;
    }

    private PensionInfo parseFullName(XmlPullParser parser, PensionInfo personOrig) {
        final String LAST_NAME = "lastName";
        final String NAME = "name";
        final String FARTHER_NAME = "fartherName";

        PensionInfo person = new PensionInfo(personOrig);

        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String attributeName = parser.getAttributeName(i);
            String attributeValue = parser.getAttributeValue(i);

            switch (attributeName) {
                case LAST_NAME:
                    person.setLastName(attributeValue);
                    break;
                case NAME:
                    person.setName(attributeValue);
                    break;
                case FARTHER_NAME:
                    person.setFartherName(attributeValue);
                    break;
            }
        }
        return person;
    }


    public List<PensionInfo> getAllPensList() {
        return allPensList;
    }

    public void setAllPensList(List<PensionInfo> allPensList) {
        this.allPensList = allPensList;
    }

    List<PensionInfo> getPensWithNumber(String numberInBase) {

        List<PensionInfo> filterPensList = new ArrayList<>(1);
        for (int i = 0; i < allPensList.size(); i++) {
            String currNuberReg = allPensList.get(i).getNumberInBase();
            boolean valueEquals = currNuberReg.equals(numberInBase);
            if (valueEquals) {
                filterPensList.add(allPensList.get(i));
            }
        }
        return filterPensList;
    }

    List<PensionInfo> getPensByLastName(String lastname) {

        List<PensionInfo> filterPensList = new ArrayList<>(10);
        for (int i = 0; i < allPensList.size(); i++) {
            String currLastname = allPensList.get(i).getLastName().toLowerCase();
            boolean valueEquals = currLastname.contains(lastname.toLowerCase());
            if (valueEquals) {
                filterPensList.add(allPensList.get(i));
            }
        }
        return filterPensList;
    }

    List<String> pensionerToStrings(List<PensionInfo> pensList) {
        List<String> listString = new ArrayList<>();
        for (int i = 0; i < pensList.size(); i++) {
            String s = "";
            s += "Номер по базе - " + pensList.get(i).getNumberInBase() + "\n";
            s += pensList.get(i).getLastName() + " ";
            s += pensList.get(i).getName() + " ";
            s += pensList.get(i).getFartherName() + "\n";
            s += "Номер по описи - " + pensList.get(i).getNumberRegistry() + "\n";
            s += pensList.get(i).getRegistryName() + "\n";
            if (!pensList.get(i).getRemark().equals(""))
                s += "Примечание - " + pensList.get(i).getRemark();

            listString.add(s);
        }
        return listString;
    }

    List<PensionInfo> getPensNumberRegistry(String numberRegistry) {
        List<PensionInfo> filterPensList = new ArrayList<>(10);
        for (int i = 0; i < allPensList.size(); i++) {
            String currNumberRegistry = allPensList.get(i).getNumberRegistry();
            boolean valueEquals = currNumberRegistry.equals(numberRegistry);
            if (valueEquals) {
                filterPensList.add(allPensList.get(i));
            }
        }
        return filterPensList;
    }
}
