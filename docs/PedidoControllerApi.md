# PedidoControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cadastrarPedido**](PedidoControllerApi.md#cadastrarPedido) | **POST** /pedidos | 
[**detalharPedido**](PedidoControllerApi.md#detalharPedido) | **GET** /pedidos/{id} | 
[**excluirPedido**](PedidoControllerApi.md#excluirPedido) | **DELETE** /pedidos/{id} | 
[**listarPedidos**](PedidoControllerApi.md#listarPedidos) | **GET** /pedidos | 

<a name="cadastrarPedido"></a>
# **cadastrarPedido**
> Object cadastrarPedido(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PedidoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PedidoControllerApi apiInstance = new PedidoControllerApi();
DadosCadastroPedido body = new DadosCadastroPedido(); // DadosCadastroPedido | 
try {
    Object result = apiInstance.cadastrarPedido(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#cadastrarPedido");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosCadastroPedido**](DadosCadastroPedido.md)|  |

### Return type

**Object**

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="detalharPedido"></a>
# **detalharPedido**
> DadosDetalhamentoPedido detalharPedido(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PedidoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PedidoControllerApi apiInstance = new PedidoControllerApi();
Long id = 789L; // Long | 
try {
    DadosDetalhamentoPedido result = apiInstance.detalharPedido(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#detalharPedido");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**DadosDetalhamentoPedido**](DadosDetalhamentoPedido.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="excluirPedido"></a>
# **excluirPedido**
> Object excluirPedido(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PedidoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PedidoControllerApi apiInstance = new PedidoControllerApi();
Long id = 789L; // Long | 
try {
    Object result = apiInstance.excluirPedido(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#excluirPedido");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

**Object**

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="listarPedidos"></a>
# **listarPedidos**
> List&lt;DadosDetalhamentoPedido&gt; listarPedidos(pageable)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.PedidoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PedidoControllerApi apiInstance = new PedidoControllerApi();
Pageable pageable = new Pageable(); // Pageable | 
try {
    List<DadosDetalhamentoPedido> result = apiInstance.listarPedidos(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#listarPedidos");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pageable** | [**Pageable**](.md)|  |

### Return type

[**List&lt;DadosDetalhamentoPedido&gt;**](DadosDetalhamentoPedido.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

