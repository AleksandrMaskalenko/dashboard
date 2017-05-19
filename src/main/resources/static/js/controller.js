angular.module('myApp', ["chart.js"])

.controller('Controller', function ($scope, $http) {

    $scope.labelsPie = ["started", "success", "error", "failure"];
    $scope.seriesBar = ["started", "success", "error", "failure"];

    var socket = io('http://testevents.neueda.lv:80', {
        path: '/live'
    });

    socket.on('test', function(data) {

        $http.post('http://localhost:8080/event/add', data);


        $http.get('http://localhost:8080/events').then(function (response) {
            $scope.events = response.data;
        });

        $http.get('http://localhost:8080/chartPie').then(function (response) {
            $scope.dataPie = response.data;
            $scope.totalEventCount = getTotalPie();

        });

        $http.get('http://localhost:8080/requirement').then(function (response) {
            $scope.labelsBar = response.data;
        });

        $http.get('http://localhost:8080/bar').then(function (response) {
            $scope.dataBar = response.data;
        });

        $http.get('http://localhost:8080/componentLabel').then(function (response) {
            $scope.componentLabel = response.data;
        });

        $http.get('http://localhost:8080/componentData').then(function (response) {
            $scope.dataComponent = response.data;
            $scope.totalComponentCount = getTotalComponent();
        });

    });


    var getTotalComponent = function(){
        var total = 0;
        var arr = $scope.dataComponent[0];
        for(var i = 0; i < arr.length; i++){
            total += arr[i];
        }
        return total;
    };

    var getTotalPie = function(){
        var total = 0;
        for(var i = 0; i < $scope.dataPie.length; i++){
            total += $scope.dataPie[i]
        }
        return total;
    };



});