package com.go;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;


import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;

public class Task {

    private String fileName1,fileName2;

    private List<ResultList> rList;

    public Task(List<ResultList> rList){
        this.rList = rList;
    }

    public Task() {
        rList = new ArrayList();
    }

    public void getTestFiles() {
        fileName1 = ClassLoader.getSystemResource("testfile1").getFile();
        fileName2 = ClassLoader.getSystemResource("testfile2").getFile();
    }

    public void readTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses() throws Exception
    {
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);

        FileList fList = new FileList();
        List <String>newList = fList.getData(file1, file2);
        String response1Str = new String();
        String response2Str = new String();
        boolean compareResult = true;


        for (int counter = 0; counter < newList.size(); counter++) {
            String[] arrOfStr = newList.get(counter).toString().split(" ");
            try {
                response1Str = given().when().get(arrOfStr[0]).getBody().asString();
                response2Str = given().when().get(arrOfStr[1]).getBody().asString();
                compareResult = compare2JSONResponse(response1Str, response2Str);
                printResultList(arrOfStr[0], arrOfStr[1], compareResult);
                StoretoResultList(arrOfStr[0], arrOfStr[1], Boolean.toString(compareResult));
            } catch (Exception e) {
                System.out.println("Exception caught within readTheHTTPRequestsFromTheTwoFilesAndCompareTheTwoResponses");
            }
        }
    }

    public void StoretoResultList(String httpRequest1, String httpRequest2, String result)
    {
        ResultList r = new ResultList(httpRequest1, httpRequest2, result);
        rList.add(r);
    }

    public void displayFinalResultList() {
        ListIterator<ResultList>
                iterator =  rList.listIterator();

        while (iterator.hasNext()) {
            System.out.println("Value is : "
                    + iterator.next().toString());
        }
    }

    /*
    ** Required by compareJSONObject
     */
    public static Set<String> getKeys(JSONObject jsonObject)
    {
        Set<String> keys = new TreeSet<String>();
        Iterator<?> iter = jsonObject.keys();
        while (iter.hasNext()) {
            keys.add((String) iter.next());
        }
        return keys;
    }

    public boolean compareJSONArray(JSONArray arr1, JSONArray arr2) throws JSONException
    {
        System.out.println("Compare JSON Array");
        if (arr1.length() != arr2.length())
            return false;
        else if ((arr1.length() == 0 && arr1.length() == 0)) //Both empty array
            return true;

        for (int i = 0; i < arr1.length(); ++i) {
            Object arr1Value = arr1.get(i);
            Object arr2Value = arr2.get(i);
            return compareValues(arr1Value, arr2Value);
        }
        return false;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static Map<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }   return map;
    }

    public boolean compareJSONObject(JSONObject obj1, JSONObject obj2) throws JSONException
    {
        System.out.println("Compare JSON Object");
        return toMap(obj1).equals(toMap(obj2));
    }

    public boolean compareValues(Object obj1, Object obj2) throws JSONException
    {
        if(obj1 instanceof Number && obj2 instanceof Number) {
            if (((Number) obj1).doubleValue() != ((Number)  obj2).doubleValue()) {
                return false;
            }
        } else if (obj1.getClass().isAssignableFrom(obj2.getClass())) {
            if (obj1 instanceof JSONArray) {
                return compareJSONArray((JSONArray)obj1, (JSONArray)obj2);
            } else if (obj1 instanceof JSONObject) {
                return compareJSONObject((JSONObject) obj1, (JSONObject) obj2);
             } else if (!obj1.equals(obj2)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean compare2JSONResponse(String response1Str, String response2Str) throws Exception
    {
        Boolean compareResult = false;

        IComparator icomparator = ((x, y) ->  {
            try {
                // Decide whether response is a JSONObject or JSONArray
                // JSONObject -> {id: 1, name: 'B'}
                // JSONArray -> [1,'value']

                Object json1 = new JSONTokener(response1Str).nextValue();
                Object json2 = new JSONTokener(response2Str).nextValue();
                if ((json1 instanceof JSONObject) && (json2 instanceof JSONArray))
                        return false;
                if ((json1 instanceof JSONArray) && (json1 instanceof JSONObject))
                        return false;
                if ((json1 instanceof JSONObject) && (json2 instanceof JSONObject))
                    return compareJSONObject((JSONObject)json1, (JSONObject)json2);
                if ((json1 instanceof JSONArray) && (json2 instanceof JSONArray))
                    return compareJSONArray((JSONArray)json1, (JSONArray)json2);

                return false;
            } catch (Exception e)
            {
                System.out.println("Exception caught within compare2JSONResponse");
                e.printStackTrace();

            }
            return false;
        });

        try {
            compareResult = icomparator.compare(response1Str, response2Str);
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception caught within compare2JSONResponse");
            compareResult = false;
        }

        return compareResult;
    }

    public void printResultList(String httpRequest1, String httpRequest2, boolean compareResult)
    {
        if (compareResult == true) {
            String responseEqualMessage = String.format("%s equals to %s", httpRequest1, httpRequest2);
            System.out.println(responseEqualMessage);
        } else {
            String responseNotEqualMessage = String.format("%s not equals to %s", httpRequest1, httpRequest2);
            System.out.println(responseNotEqualMessage);
        }
    }

    public void parallelProcess() {
        int n = 4; // Number of threads
        for (int i = 0; i < n; i++) {
            MultiTask t = new  MultiTask();
            t.start();
        }
    }

}
