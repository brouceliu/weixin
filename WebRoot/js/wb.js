$(document).ready(function(){
	
	var u = navigator.userAgent, app = navigator.appVersion; 
	
	
	var wechatInfo = navigator.userAgent.match(/MicroMessenger\/([\d\.]+)/i) ;
	if( !wechatInfo ) {
	  //  alert("本活动仅支持微信") ;
	} else if ( wechatInfo[1] < "6.0" ) {
	//    alert("本活动仅支持微信5.0以上版本") ;
	}
	
	
	
	
});
 citys=$("#a1").val();

var app = angular.module('weather', [], function($httpProvider){
    publicFun.angularPostConfig($httpProvider)
});

app.controller('w1Controller', function($scope,$http) {
	var url="http://wx.wushang.com/Txwx/servlet/weather";
	
	var params = {city:citys};
	 $http.post(url,params).success(function(ret){
		 if(ret){
			
			 var data=ret.date;
			 var result=ret.results[0];
			 $scope.index=result.index;
			 $scope.wlist=result.weather_data;
			 $scope.wb={city:citys,pm:result.pm25};
			 
		 }
		 
	 });
});
var publicFun = {
		 angularPostConfig:function(httpProvider){
		        httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
		        httpProvider.defaults.headers.post['Accept'] = 'application/json, text/javascript, */*; q=0.01';
		        httpProvider.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';

		        var param = function (obj) {
		            var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
		            for (name in obj) {
		                value = obj[name];

		                if (value instanceof Array) {
		                    for (i = 0; i < value.length; ++i) {
		                        subValue = value[i];
		                        fullSubName = name + '[' + i + ']';
		                        innerObj = {};
		                        innerObj[fullSubName] = subValue;
		                        query += param(innerObj) + '&';
		                    }
		                }
		                else if (value instanceof Object) {
		                    for (subName in value) {
		                        subValue = value[subName];
		                        fullSubName = name + '[' + subName + ']';
		                        innerObj = {};
		                        innerObj[fullSubName] = subValue;
		                        query += param(innerObj) + '&';
		                    }
		                }
		                else if (value !== undefined && value !== null)
		                    query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
		            }

		            return query.length ? query.substr(0, query.length - 1) : query;
		        };

		        httpProvider.defaults.transformRequest = [function (data) {
		            return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
		        }];
		    }
		
		
};