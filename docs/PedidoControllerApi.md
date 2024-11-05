# PedidoControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cadastrar1**](PedidoControllerApi.md#cadastrar1) | **POST** /pedidos | 
[**detalhar2**](PedidoControllerApi.md#detalhar2) | **GET** /pedidos/{id} | 
[**excluir**](PedidoControllerApi.md#excluir) | **DELETE** /pedidos/{id} | 
[**listar2**](PedidoControllerApi.md#listar2) | **GET** /pedidos | 

<a name="cadastrar1"></a>
# **cadastrar1**
> DadosDetalhamentoPedido cadastrar1(body)



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
    DadosDetalhamentoPedido result = apiInstance.cadastrar1(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#cadastrar1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosCadastroPedido**](DadosCadastroPedido.md)|  |

### Return type

[**DadosDetalhamentoPedido**](DadosDetalhamentoPedido.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="detalhar2"></a>
# **detalhar2**
> DadosDetalhamentoPedido detalhar2(id)



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
    DadosDetalhamentoPedido result = apiInstance.detalhar2(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#detalhar2");
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

<a name="excluir"></a>
# **excluir**
> Object excluir(id)



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
    Object result = apiInstance.excluir(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#excluir");
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

<a name="listar2"></a>
# **listar2**
> List&lt;DadosDetalhamentoPedido&gt; listar2(pageable)



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
    List<DadosDetalhamentoPedido> result = apiInstance.listar2(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PedidoControllerApi#listar2");
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

