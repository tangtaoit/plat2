package com.tt.plat.component.form.select2;

public class JQuery {


    private JQuery() {
    }
    
    
    public static String execute(String script, Object... params) {
	return "(function($) { "+String.format(script, params)+" })(jQuery);";
    }



    public static String func(String script,Object...params){
        return "function($) { "+String.format(script, params)+" };";
    }

    public static String runJs(String script,Object...params){

        return String.format(script, params);
    }
}
