angular.module('myApp', ["chart.js"])

.controller('Controller', function ($scope, $http) {

    $scope.eventLabels = ["started", "success", "error", "failure"];
    $scope.requirementSeries = ["started", "success", "error", "failure"];

    var socket = io('http://testevents.neueda.lv:80', {
        path: '/live'
    });

    socket.on('test', function(data) {

        $http.post('http://localhost:8080/event/add', data);

        $http.get('http://localhost:8080/events').then(function (response) {
            $scope.events = response.data;
        });

        $http.get('http://localhost:8080/eventData').then(function (response) {
            $scope.eventData = response.data;
            $scope.totalEventCount = getTotalPie();

        });

        $http.get('http://localhost:8080/requirementLabel').then(function (response) {
            $scope.requirementLabel = response.data;
        });

        $http.get('http://localhost:8080/requirementData').then(function (response) {
            $scope.requirementData = response.data;
        });

        $http.get('http://localhost:8080/componentLabel').then(function (response) {
            $scope.componentLabel = response.data;
        });

        $http.get('http://localhost:8080/componentData').then(function (response) {
            $scope.componentData = response.data;
            $scope.totalComponentCount = getTotalComponent();
        });

    });


    var getTotalComponent = function(){
        var total = 0;
        var arr = $scope.componentData[0];
        for(var i = 0; i < arr.length; i++){
            total += arr[i];
        }
        return total;
    };

    var getTotalPie = function(){
        var total = 0;
        for(var i = 0; i < $scope.eventData.length; i++){
            total += $scope.eventData[i]
        }
        return total;
    };

});