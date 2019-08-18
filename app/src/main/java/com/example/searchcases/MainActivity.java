package com.example.searchcases;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<String>();
/*
        try {
            list = extractFromXML();
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }

        setListAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list));
        */
        parseRegistry();
    }

    public ArrayList<String> extractFromXML() throws XmlPullParserException, IOException {
        ArrayList<String> list = new ArrayList<String>();
        XmlPullParser parser = getResources().getXml(R.xml.contacts);

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            int eventType = parser.getEventType();
            String name = parser.getName();


            if (eventType == XmlPullParser.START_TAG && name.equals("contact")) {
                list.add(parser.getAttributeValue(0) + " "
                        + parser.getAttributeValue(1) + "\n"
                        + parser.getAttributeValue(2));
            }
            parser.next();
        }
        return list;
    }

    public void parseRegistry() {
        PensionInfo person = null;
        String registryName = "";
        try {
            XmlPullParser parser = getResources().getXml(R.xml.export_2019);
            person = fillPensioner(parser);
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private String parseRegistryName(XmlPullParser parser) {
        if (parser.getAttributeCount() == 0) {
            return null;
        }
        String attributeName = parser.getAttributeName(0);
        String attributeValue = parser.getAttributeValue(0);
        String attributeName2 = parser.getAttributeValue(null, "name");
        return attributeValue;
    }

    private PensionInfo parsePerson(XmlPullParser parser, PensionInfo personOrig) {
        String nameTag = parser.getName();
        PensionInfo person = new PensionInfo(personOrig);

        final String NUMBER_IN_BASE = "numberInBase";
        final String NUMBER_REGISTR = "numberRegistr";
        final String DATA_START = "dataStart";
        final String DATA_END = "dataEnd";
        final String PAGE_COUNT = "pageCount";
        final String REMARK = "remark";

        parser.getAttributeValue(null,NUMBER_IN_BASE);
        parser.getAttributeValue(null,NUMBER_REGISTR);
        parser.getAttributeValue(null,DATA_START);
        parser.getAttributeValue(null,DATA_END);
        parser.getAttributeValue(null,PAGE_COUNT);
        parser.getAttributeValue(null,REMARK);


        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String attributeName = parser.getAttributeName(i);
            String attributeValue = parser.getAttributeValue(i);

            switch (attributeName) {
                case "numberInBase":
                    person.setNumberInBase(attributeValue);
                    break;
                case "numberRegistry":
                    person.setNumberRegistry(attributeValue);
                    break;
                case "dataStart":
                    person.setDataStart(attributeValue);
                    break;
                case "dataEnd":
                    person.setDataEnd(attributeValue);
                    break;
                case "pageCount":
                    person.setPageCount(attributeValue);
                    break;
                case "remark":
                    person.setRemark(attributeValue);
                    break;

            }
        }
        return person;
    }

    private PensionInfo parseFullName(XmlPullParser parser, PensionInfo personOrig) {
        PensionInfo person = new PensionInfo(personOrig);
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String attributeName = parser.getAttributeName(i);
            String attributeValue = parser.getAttributeValue(i);

            switch (attributeName) {
                case "lastName":
                    person.setLastName(attributeValue);
                    break;
                case "name":
                    person.setName(attributeValue);
                    break;
                case "fartherName":
                    person.setFartherName(attributeValue);
                    break;
                default:
                    person.setRegistryName("2019");
                    break;
            }
        }
        return person;
    }

    private PensionInfo fillPensioner(XmlPullParser parser) throws XmlPullParserException, IOException {
        PensionInfo person = new PensionInfo();

        final String PERSON_TAG = "person";
        final String FULL_NAME_TAG = "fullName";
        final String REGISTER_TAG = "Register";

        final String LAST_NAME = "lastName";
        final String NAME = "name";
        final String FARTHER_NAME = "fartherName";


        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            int eventType = parser.getEventType();
            // начало тэга
            if (eventType == XmlPullParser.START_TAG) {
                //имя тега
                // parser.getName();
                //уровень тега
                //parser.getDepth();
                String nameTag = parser.getName();
                switch (nameTag){
                    case PERSON_TAG:
                        person = parsePerson(parser, person);
                        break;
                    case FULL_NAME_TAG:
                        person = parseFullName(parser, person);
                        break;
                    case REGISTER_TAG:
                        parseRegistryName(parser);
                        break;
                }
            }
            if (eventType == XmlPullParser.END_TAG && parser.getName() == PERSON_TAG)
            {
                if (person != null)
                    person.setRegistryName(registryName);
                //languages.add(lang);
            }
            parser.next();
        }


        return person;
    }

    public void someM() {
        try {
            XmlPullParser parser = getResources().getXml(R.xml.export_2019);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                final String TAG = "ЛогКот";
                String tmp = "";

                switch (parser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "Начало документа");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        Log.d(TAG,
                                "START_TAG: имя тега = " + parser.getName()
                                        + ", уровень = " + parser.getDepth()
                                        + ", число атрибутов = "
                                        + parser.getAttributeCount());
                        tmp = "";
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            tmp = tmp + parser.getAttributeName(i) + " = "
                                    + parser.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.d(TAG, "Атрибуты: " + tmp);
                        break;
                    // конец тега
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "END_TAG: имя тега = " + parser.getName());
                        break;
                    // содержимое тега
                    case XmlPullParser.TEXT:
                        Log.d(TAG, "текст = " + parser.getText());
                        break;

                    default:
                        break;
                }
                parser.next();
            }
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
