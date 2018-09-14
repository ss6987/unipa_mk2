package etc;

public class ReplaceString {
    String[][] replaceList = {
//            {"&","&amp;"},
            {"　"," "},
            {"\"","&quot;"},
            {"'","&#39;"},
            {"<","&lt;"},
            {">","&gt;"},
            {" ","&nbsp;"},
            {"©","&copy;"},
            {"\t",""}
    };

    public String replace(String string){
        for(int i = 0;i < replaceList.length;i++){
            string = string.replace(replaceList[i][0],replaceList[i][1]);
        }
        string = string.replace("&lt;br/&gt;","<br/>");
        return string;
    }
}
