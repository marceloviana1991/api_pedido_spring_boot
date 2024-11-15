# ProdutoControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adicionarProdutoEmEstoque**](ProdutoControllerApi.md#adicionarProdutoEmEstoque) | **POST** /produtos/estoque | 
[**atualizarProduto**](ProdutoControllerApi.md#atualizarProduto) | **PUT** /produtos | 
[**cadastrarProduto**](ProdutoControllerApi.md#cadastrarProduto) | **POST** /produtos | 
[**detalharProduto**](ProdutoControllerApi.md#detalharProduto) | **GET** /produtos/{id} | 
[**listarProdutos**](ProdutoControllerApi.md#listarProdutos) | **GET** /produtos | 
[**uploadDeFotoDeProduto**](ProdutoControllerApi.md#uploadDeFotoDeProduto) | **POST** /produtos/foto/{id} | 

<a name="adicionarProdutoEmEstoque"></a>
# **adicionarProdutoEmEstoque**
> DadosDetalhamentoProduto adicionarProdutoEmEstoque(body)



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
    DadosDetalhamentoProduto result = apiInstance.adicionarProdutoEmEstoque(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#adicionarProdutoEmEstoque");
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

<a name="atualizarProduto"></a>
# **atualizarProduto**
> DadosDetalhamentoProduto atualizarProduto(body)



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
    DadosDetalhamentoProduto result = apiInstance.atualizarProduto(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#atualizarProduto");
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

<a name="cadastrarProduto"></a>
# **cadastrarProduto**
> DadosDetalhamentoProduto cadastrarProduto(body)



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
    DadosDetalhamentoProduto result = apiInstance.cadastrarProduto(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#cadastrarProduto");
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

<a name="detalharProduto"></a>
# **detalharProduto**
> DadosDetalhamentoProduto detalharProduto(id)



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
    DadosDetalhamentoProduto result = apiInstance.detalharProduto(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#detalharProduto");
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

<a name="listarProdutos"></a>
# **listarProdutos**
> List&lt;DadosDetalhamentoProduto&gt; listarProdutos(pageable)



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
    List<DadosDetalhamentoProduto> result = apiInstance.listarProdutos(pageable);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#listarProdutos");
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

<a name="uploadDeFotoDeProduto"></a>
# **uploadDeFotoDeProduto**
> Object uploadDeFotoDeProduto(id, body)



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
    Object result = apiInstance.uploadDeFotoDeProduto(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProdutoControllerApi#uploadDeFotoDeProduto");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |
 **body** | [**FotoIdBody**](FotoIdBody.md)|  | [optional]

### Return type

**Object**

### Authorization

[bearer-key](../README.md#bearer-key)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

