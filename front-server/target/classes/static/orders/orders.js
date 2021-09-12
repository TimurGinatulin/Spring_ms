angular.module('app').controller('ordersController', function ($scope, $http,$localStorage) {
    const contextPath = 'http://localhost:5555';

    $scope.showMyOrders = function () {
    $scope.user = $localStorage.currentUser.email;
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.showMyOrders();
});