/**
 * Created by admin on 2014/8/18.
 */
TableTools.BUTTONS.selectedBtn = {
    "sAction": "text",
    "sTag": "default",
    "sFieldBoundary": "",
    "sFieldSeperator": "\t",
    "sNewLine": "<br>",
    "sToolTip": "",
    "sButtonClass": "DTTT_button_text",
    "sButtonClassHover": "DTTT_button_text_hover",
    "sButtonText": "提交",
    "mColumns": "all",
    "bHeader": false,
    "bFooter": false,
    "sDiv": "",
    "fnMouseover": null,
    "fnMouseout": null,
    data: null,
    success: null,
    dataType: "json",
    tableMarkId: null,
    mainProp:null,
    "fnClick": function (nButton, oConfig) {

        var oTT = TableTools.fnGetInstance(''+oConfig.tableMarkId+'');

        var aData = oTT.fnGetSelectedData();
        var mainPropValues ="";

        for(var index in aData)
        {
            mainPropValues+=aData[index][oConfig.mainProp]+",";

        }
        if(mainPropValues.length>0){
            mainPropValues = mainPropValues.substring(0,mainPropValues.length-1);
        }

        var mainData ={};

        mainData.action="selected";
        mainData.data=mainPropValues;
        mainData.uid=oConfig.uid;



        $.ajax({
            type: 'POST',
            url: oConfig.sAjaxUrl,
            data: mainData,
            success: oConfig.success,
            dataType: oConfig.dataType
        });
    },
    "fnSelect": null,
    "fnComplete": null,
    "fnInit": null
};