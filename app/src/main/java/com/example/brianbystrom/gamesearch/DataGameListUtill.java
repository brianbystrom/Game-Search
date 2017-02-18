/*
Assignment #: In Class 05
File Name: DataUtil.java
Group Members: Brian Bystrom
*/

package com.example.brianbystrom.gamesearch;

import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class DataGameListUtill {

    static public class DataSAXParser extends DefaultHandler {
        ArrayList<Data> dataList = new ArrayList<Data>();
        ArrayList<String> similar = new ArrayList<String>();
        Data data;
        StringBuilder xmlInnerText;
        ArrayList<String> genres = new ArrayList<String>();
        int gCounter = 0;
        int sCounter = 0;


        static public ArrayList<Data> parseData(InputStream in) {

            DataSAXParser parser = new DataSAXParser();
            try {
                Xml.parse(in, Xml.Encoding.UTF_8, parser);
                return parser.getDataList();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }

        public ArrayList<Data> getDataList() {
            return dataList;
        }


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            dataList = new ArrayList<Data>();
            xmlInnerText = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            super.startElement(uri, localName, qName, attributes);
                if(localName.equals("Game")) {
                    data = new Data();
                    sCounter++;
                }





        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            if(localName.equals("Game")) {
                dataList.add(data);
            } else if (localName.equals("Genres")) {
                data.setGenres(genres);
            } else if (localName.equals("Similar")) {
                data.setSimilar(similar);
            }

            if(data != null) {
                if(localName.equals("GameTitle")) {
                    data.setTitle(xmlInnerText.toString().trim());
                    Log.d("UTIL", "TITLE" + xmlInnerText.toString());
                } else if(localName.equals("ReleaseDate")) {
                    data.setReleaseDate((xmlInnerText.toString().trim()));
                    //Log.d("UTIL", "PUB DATE" + xmlInnerText.toString());
                } else if(localName.equals("Platform")) {
                    data.setPlatform(xmlInnerText.toString().trim());
                    //Log.d("UTIL", "DESC" + xmlInnerText.toString());
                } else if(localName.equals("id")) {
                    // Log.d("UTIL", "IMAGE" + xmlInnerText.toString());
                    sCounter++;
                    data.setId(xmlInnerText.toString().trim());
                } else if(localName.equals("Overview")) {
                    // Log.d("UTIL", "IMAGE" + xmlInnerText.toString());
                    data.setOverview(xmlInnerText.toString().trim());
                } else if(localName.equals("Genre")) {
                    // Log.d("UTIL", "IMAGE" + xmlInnerText.toString());
                    //data.setGenre(xmlInnerText.toString());
                } else if(localName.equals("Publisher")) {
                    // Log.d("UTIL", "IMAGE" + xmlInnerText.toString());
                    data.setPublisher(xmlInnerText.toString().trim());
                }





            }

            xmlInnerText.setLength(0);

        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            dataList.add(data);
        }



        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch, start, length);
        }


    }
}
