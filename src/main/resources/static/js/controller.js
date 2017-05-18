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
        });

        $http.get('http://localhost:8080/requirement').then(function (response) {
            $scope.labelsBar = response.data;
        });

        $http.get('http://localhost:8080/bar').then(function (response) {
            $scope.dataBar = response.data;
        });

    });

});