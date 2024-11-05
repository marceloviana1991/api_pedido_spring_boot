# UsuarioControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**atualizarUsuario**](UsuarioControllerApi.md#atualizarUsuario) | **PUT** /usuarios | 
[**detalharUsuario**](UsuarioControllerApi.md#detalharUsuario) | **GET** /usuarios/{id} | 
[**listarUsuarios**](UsuarioControllerApi.md#listarUsuarios) | **GET** /usuarios | 

<a name="atualizarUsuario"></a>
# **atualizarUsuario**
> DadosDetalhamentoUsuario atualizarUsuario(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsuarioControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


UsuarioControllerApi apiInstance = new UsuarioControllerApi();
DadosAtualizacaoUsuario body = new DadosAtualizacaoUsuario(); // DadosAtualizacaoUsuario | 
try {
    DadosDetalhamentoUsuario result = apiInstance.atualizarUsuario(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#atualizarUsuario");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosAtualizacaoUsuario**](DadosAtualizacaoUsuario.md)|  |

### Return type

[**DadosDetalhamentoUsuario**](DadosDetalhamentoUsuario.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="detalharUsuario"></a>
# **detalharUsuario**
> DadosDetalhamentoUsuario detalharUsuario(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsuarioControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


UsuarioControllerApi apiInstance = new UsuarioControllerApi();
Long id = 789L; // Long | 
try {
    DadosDetalhamentoUsuario result = apiInstance.detalharUsuario(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#detalharUsuario");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**DadosDetalhamentoUsuario**](DadosDetalhamentoUsuario.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="listarUsuarios"></a>
# **listarUsuarios**
> List&lt;DadosDetalhamentoUsuario&gt; listarUsuarios(pageable)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UsuarioControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


UsuarioControllerApi apiInstance = new UsuarioControllerApi();
Pageable pageable = new Pageable(); // Pageable | 
try {
    List<DadosDetalhamentoUsuario> result = apiInstance.listarUsuarios(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#listarUsuarios");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pageable** | [**Pageable**](.md)|  |

### Return type

[**List&lt;DadosDetalhamentoUsuario&gt;**](DadosDetalhamentoUsuario.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

