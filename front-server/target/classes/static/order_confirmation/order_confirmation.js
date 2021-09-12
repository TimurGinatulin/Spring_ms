angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.happyCartUuid)
            .then(function (response) {
                $scope.happyUserCart = response.data;
            });
    }

    $scope.submitOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                cartUuid: $localStorage.happyCartUuid,
                address: $scope.order_info.address
            }
        }).then(function (response) {
            createDelivery(response.data.id);
            $location.path('/order_result/' + response.data.id);
        });


    createDelivery = function(order_id){
    $http({
                url: contextPath + '/api/v1/deliveries',
                method: 'POST',
                params: {
                    orderId: order_id,
                    address: $scope.order_info.address
                }
            }).then(function (response) {
                $location.path('/order_result/' + response.data.id);
            });
        }
    }

    $scope.loadCart();
});