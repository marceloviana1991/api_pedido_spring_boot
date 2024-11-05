# ProdutoControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adicionarEmEstoque**](ProdutoControllerApi.md#adicionarEmEstoque) | **POST** /produtos/estoque | 
[**atualizar1**](ProdutoControllerApi.md#atualizar1) | **PUT** /produtos | 
[**cadastrar**](ProdutoControllerApi.md#cadastrar) | **POST** /produtos | 
[**detalhar1**](ProdutoControllerApi.md#detalhar1) | **GET** /produtos/{id} | 
[**listar1**](ProdutoControllerApi.md#listar1) | **GET** /produtos | 
[**upload**](ProdutoControllerApi.md#upload) | **POST** /produtos/foto/{id} | 

<a name="adicionarEmEstoque"></a>
# **adicionarEmEstoque**
> DadosDetalhamentoProduto adicionarEmEstoque(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
DadosCadastroProdutoEstoque body = new DadosCadastroProdutoEstoque(); // DadosCadastroProdutoEstoque | 
try {
    DadosDetalhamentoProduto result = apiInstance.adicionarEmEstoque(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#adicionarEmEstoque");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosCadastroProdutoEstoque**](DadosCadastroProdutoEstoque.md)|  |

### Return type

[**DadosDetalhamentoProduto**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="atualizar1"></a>
# **atualizar1**
> DadosDetalhamentoProduto atualizar1(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
DadosAtualizacaoProduto body = new DadosAtualizacaoProduto(); // DadosAtualizacaoProduto | 
try {
    DadosDetalhamentoProduto result = apiInstance.atualizar1(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#atualizar1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosAtualizacaoProduto**](DadosAtualizacaoProduto.md)|  |

### Return type

[**DadosDetalhamentoProduto**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="cadastrar"></a>
# **cadastrar**
> DadosDetalhamentoProduto cadastrar(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
DadosCadastroProduto body = new DadosCadastroProduto(); // DadosCadastroProduto | 
try {
    DadosDetalhamentoProduto result = apiInstance.cadastrar(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#cadastrar");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadosCadastroProduto**](DadosCadastroProduto.md)|  |

### Return type

[**DadosDetalhamentoProduto**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="detalhar1"></a>
# **detalhar1**
> DadosDetalhamentoProduto detalhar1(id)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
Long id = 789L; // Long | 
try {
    DadosDetalhamentoProduto result = apiInstance.detalhar1(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#detalhar1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**DadosDetalhamentoProduto**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="listar1"></a>
# **listar1**
> List&lt;DadosDetalhamentoProduto&gt; listar1(pageable)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
Pageable pageable = new Pageable(); // Pageable | 
try {
    List<DadosDetalhamentoProduto> result = apiInstance.listar1(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#listar1");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pageable** | [**Pageable**](.md)|  |

### Return type

[**List&lt;DadosDetalhamentoProduto&gt;**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="upload"></a>
# **upload**
> DadosDetalhamentoProduto upload(id, body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ProdutoControllerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ProdutoControllerApi apiInstance = new ProdutoControllerApi();
Long id = 789L; // Long | 
FotoIdBody body = new FotoIdBody(); // FotoIdBody | 
try {
    DadosDetalhamentoProduto result = apiInstance.upload(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#upload");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |
 **body** | [**FotoIdBody**](FotoIdBody.md)|  | [optional]

### Return type

[**DadosDetalhamentoProduto**](DadosDetalhamentoProduto.md)

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

