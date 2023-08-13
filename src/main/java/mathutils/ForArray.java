package mathutils;

import java.util.StringTokenizer;

public class ForArray {
    public int findMax(int arr[]){
        int mx = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            mx = Math.max(mx, arr[i]);
        }
        return mx;
    }
    public String reverseString(String str){
        StringBuffer ans = new StringBuffer();
        StringTokenizer temp = new StringTokenizer(str, " ");

        while(temp.hasMoreElements()){
            StringBuffer s = new StringBuffer(temp.nextToken());
            s.reverse();

            ans.append(s);
            ans.append(" ");
        }
        return ans.toString();
    }
}
