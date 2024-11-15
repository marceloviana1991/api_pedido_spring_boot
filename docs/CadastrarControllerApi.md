# CadastrarControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cadastrarUsuario**](CadastrarControllerApi.md#cadastrarUsuario) | **POST** /cadastrar | 
[**reenviarEmail**](CadastrarControllerApi.md#reenviarEmail) | **GET** /cadastrar/reenviar/{id} | 
[**verificarCadastroDeUsuario**](CadastrarControllerApi.md#verificarCadastroDeUsuario) | **GET** /cadastrar/{uuid} | 

<a name="cadastrarUsuario"></a>
# **cadastrarUsuario**
> DadosDetalhamentoUsuario cadastrarUsuario(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CadastrarControllerApi;


CadastrarControllerApi apiInstance = new CadastrarControllerApi();
DadosCadastroUsuario body = new DadosCadastroUsuario(); // DadosCadastroUsuario | 
try {
    DadosDetalhamentoUsuario result = apiInstance.cadastrarUsuario(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CadastrarControllerApi#cadastrarUsuario");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosCadastroUsuario**](DadosCadastroUsuario.md)|  |

### Return type

[**DadosDetalhamentoUsuario**](DadosDetalhamentoUsuario.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="reenviarEmail"></a>
# **reenviarEmail**
> DadosDetalhamentoUsuario reenviarEmail(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CadastrarControllerApi;


CadastrarControllerApi apiInstance = new CadastrarControllerApi();
Long id = 789L; // Long | 
try {
    DadosDetalhamentoUsuario result = apiInstance.reenviarEmail(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CadastrarControllerApi#reenviarEmail");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="verificarCadastroDeUsuario"></a>
# **verificarCadastroDeUsuario**
> Object verificarCadastroDeUsuario(uuid)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CadastrarControllerApi;


CadastrarControllerApi apiInstance = new CadastrarControllerApi();
String uuid = "uuid_example"; // String | 
try {
    Object result = apiInstance.verificarCadastroDeUsuario(uuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CadastrarControllerApi#verificarCadastroDeUsuario");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uuid** | **String**|  |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

