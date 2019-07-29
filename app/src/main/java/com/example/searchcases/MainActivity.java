package com.example.searchcases;

//import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[]  listItemDemo = {"Java","Kotlin","C++" };
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView)findViewById(R.id.listView);
        final ArrayAdapter<String> adapterDemo = new ArrayAdapter<>(this,
                R.layout.list_item, listItemDemo);
        listView.setAdapter(adapterDemo);
        }
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

/*
        //ListView listView = (ListView) findViewById(R.id.list);
        List<PensionInfo> personList = parseRegistry();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < personList.size(); i++) {
            String fartherName = personList.get(i).getFartherName();
            String name = personList.get(i).getName();
            String lastName = personList.get(i).getLastName();
            String numberRegistry = personList.get(i).getNumberRegistry();
            String numberInBase = personList.get(i).getNumberInBase();

            list.add(lastName + " " + name + " " + fartherName + " - "
                    +  numberInBase + " **** " + numberRegistry);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

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

    public List<PensionInfo> parseRegistry() {
        List<PensionInfo> personList = null;
        try {
            XmlPullParser parser = getResources().getXml(R.xml.export_2019);
            personList = fillOutPensionerList(parser);
        } catch (Throwable t) {
            Toast.makeText(this,
                    "Ошибка при загрузке XML-документа: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
        String ggg = "GGG";
        return personList;
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
                        parseRegistryName(parser);
                        break;
                }
            }
            if (eventType == XmlPullParser.END_TAG && parser.getName() == PERSON_TAG) {
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
        String attributeValue = parser.getAttributeValue(0);
        return attributeValue;
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
    */

}
