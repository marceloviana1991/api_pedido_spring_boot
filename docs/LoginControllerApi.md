# LoginControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**efetuarLogin**](LoginControllerApi.md#efetuarLogin) | **POST** /login | 

<a name="efetuarLogin"></a>
# **efetuarLogin**
> Object efetuarLogin(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LoginControllerApi;


LoginControllerApi apiInstance = new LoginControllerApi();
DadosAutenticacaoUsuario body = new DadosAutenticacaoUsuario(); // DadosAutenticacaoUsuario | 
try {
    Object result = apiInstance.efetuarLogin(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LoginControllerApi#efetuarLogin");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosAutenticacaoUsuario**](DadosAutenticacaoUsuario.md)|  |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

