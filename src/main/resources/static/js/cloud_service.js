
'use strict';

(function() {
        var entries = [];
		  
        var settings = {
			entries: [],
            width: 480,
            height: 480,
            radius: '65%',
            radiusMin: 75,
            bgDraw: true,
            bgColor: '#111',
            opacityOver: 1.00,
            opacityOut: 0.05,
            opacitySpeed: 6,
            fov: 800,
            speed: 2,
            fontFamily: 'Oswald, Arial, sans-serif',
            fontSize: '15',
            fontColor: '#fff',
            fontWeight: 'normal',//bold
            fontStyle: 'normal',//italic 
            fontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
            fontToUpperCase: true,
            tooltipFontFamily: 'Oswald, Arial, sans-serif',
                tooltipFontSize: '11',
                tooltipFontColor: '#fff',
                tooltipFontWeight: 'normal',//bold
                tooltipFontStyle: 'normal',//italic 
                tooltipFontStretch: 'normal',//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
                tooltipFontToUpperCase: false,
                tooltipTextAnchor: 'left',
                tooltipDiffX: 0,
                tooltipDiffY: 10
        };
			
        $.getJSON("http://127.0.0.1:8080/tag/", function( data ) {
            for (var i = 0; i < data.length; i++) {
                entries[i] = {
					label: data[i].name,
					url: "http://127.0.0.1:8080/list?tag=" + data[i].name,
                    target: '_top'
				}
            }
			settings.entries = entries;
			$('#tag_cloud').svg3DTagCloud(settings);
        });
})();
   
