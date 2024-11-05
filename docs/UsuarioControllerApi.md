# UsuarioControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**atualizar**](UsuarioControllerApi.md#atualizar) | **PUT** /usuarios | 
[**detalhar**](UsuarioControllerApi.md#detalhar) | **GET** /usuarios/{id} | 
[**listar**](UsuarioControllerApi.md#listar) | **GET** /usuarios | 

<a name="atualizar"></a>
# **atualizar**
> DadosDetalhamentoUsuario atualizar(body)



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
    DadosDetalhamentoUsuario result = apiInstance.atualizar(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#atualizar");
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

<a name="detalhar"></a>
# **detalhar**
> DadosDetalhamentoUsuario detalhar(id)



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
    DadosDetalhamentoUsuario result = apiInstance.detalhar(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#detalhar");
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

<a name="listar"></a>
# **listar**
> List&lt;DadosDetalhamentoUsuario&gt; listar(pageable)



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
    List<DadosDetalhamentoUsuario> result = apiInstance.listar(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsuarioControllerApi#listar");
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

