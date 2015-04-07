/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Javascript modal window
 * Licensed under the Apache License, Version 2.0
 * @author Matej Knopp
 */

;(function (undefined) {
    'use strict';

    /**
     * Returns the modal window markup with specified element identifiers.
     */
    Wicket.Window.getMarkup = function(idWindow, idClassElement, idCaption, idContent, idTop, idTopLeft, idTopRight, idLeft, idRight, idBottomLeft, idBottomRight, idBottom, idCaptionText, isFrame) {
        var s =
            "<div class=\"wicket-modal\" id=\""+idWindow+"\" role=\"dialog\" aria-labelledBy=\""+idCaptionText+"\" style=\"top: 10px; left: 10px; width: 100px;\"><form style='background-color:transparent;padding:0px;margin:0px;border-width:0px;position:static'>"+
            "<div id=\""+idClassElement+"\">"+

            "<div class=\"w_top_1\">"+

            "<div class=\"w_topLeft\" id=\""+idTopLeft+"\">"+
            "</div>"+

            "<div class=\"w_topRight\" id=\""+idTopRight+"\">"+
            "</div>"+

            "<div class=\"w_top\" id='"+idTop+"'>"+
            "</div>"+

            "</div>"+

            "<div class=\"w_left\" id='"+idLeft+"'>"+
            "<div class=\"w_right_1\">"+
            "<div class=\"w_right\" id='"+idRight+"'>"+
            "<div class=\"w_content_1\" onmousedown=\"Wicket.Event.stop(event);\">"+
            "<div class=\"w_caption\"  id=\""+idCaption+"\">"+
            "<a class=\"w_close\" style=\"z-index:1\" href=\"#\"></a>"+
            "<h3 id=\""+idCaptionText+"\" class=\"w_captionText\"></h3>"+
            "</div>"+

            "<div class=\"w_content\">";

        if (isFrame) {
            s+= "<iframe";
            if (Wicket.Browser.isIELessThan7()) {
                s+= " src=\"about:blank\"";
            }
            s+= " frameborder=\"0\" id=\""+idContent+"\" allowtransparency=\"false\" style=\"height: 200px\" class=\"wicket_modal\"></iframe>";
        } else {
            var styleIE7 = Wicket.Browser.isIE7() ? "style='z-index: 20001'" : "";
            s+= "<div id='"+idContent+"' class='w_content_container' " + styleIE7 + "></div>";
        }

        s+=
            "</div>"+
            "</div>"+
            "</div>"+
            "</div>"+
            "</div>"+


            "<div class=\"w_bottom_1\" id=\""+idBottom+"_1\">"+

            "<div class=\"w_bottomRight\"  id=\""+idBottomRight+"\">"+
            "</div>"+

            "<div class=\"w_bottomLeft\" id=\""+idBottomLeft+"\">"+
            "</div>"+

            "<div class=\"w_bottom\" id=\""+idBottom+"\">"+
            "</div>"+


            "</div>"+

            "</div>"+
            "</form></div>";

        return s;
    };

})();
