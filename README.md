
# API Rest de sistema de gerenciamento de cadastro de pedidos

Olá, me chamo Marcelo Viana. Sou iniciante em SpringBoot e criei esse repositório para compartilhar uma experiência.

Recentemente estava criando um sistema de gerenciamento de pedidos e não soube como criar uma entidade que representasse o conjunto de itens de um pedido. Após algumas pesquisas encontrei essa solução e estou compartilhando com a comunidade.

Imagem do projeto representado por DER.

![DER](imagens/der.png)

Além do sistema de gerenciamento de pedidos, o código fonte contém:
- Conceitos de qualidade de software baseados nos princípios SOLID;
- Sistema de segurança que contém gerenciamento de acesso multicamada com níveis de permissões de acesso diferentes para usuário comum e usuário administrador;
- Ativação de cadastro por email.

Para implementação do sistema de ativação de usuário por email é necessário ter conta gmail e criar uma senha de app. Essa senha pode ser criada pelo próprio sistema de gerenciamento de contas do google.

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*CadastrarControllerApi* | [**cadastrar2**](docs/CadastrarControllerApi.md#cadastrar2) | **POST** /cadastrar | 
*CadastrarControllerApi* | [**verificarCadastro**](docs/CadastrarControllerApi.md#verificarCadastro) | **GET** /cadastrar/{uuid} | 
*LoginControllerApi* | [**efetuarLogin**](docs/LoginControllerApi.md#efetuarLogin) | **POST** /login | 
*PedidoControllerApi* | [**cadastrar1**](docs/PedidoControllerApi.md#cadastrar1) | **POST** /pedidos | 
*PedidoControllerApi* | [**detalhar2**](docs/PedidoControllerApi.md#detalhar2) | **GET** /pedidos/{id} | 
*PedidoControllerApi* | [**excluir**](docs/PedidoControllerApi.md#excluir) | **DELETE** /pedidos/{id} | 
*PedidoControllerApi* | [**listar2**](docs/PedidoControllerApi.md#listar2) | **GET** /pedidos | 
*ProdutoControllerApi* | [**adicionarEmEstoque**](docs/ProdutoControllerApi.md#adicionarEmEstoque) | **POST** /produtos/estoque | 
*ProdutoControllerApi* | [**atualizar1**](docs/ProdutoControllerApi.md#atualizar1) | **PUT** /produtos | 
*ProdutoControllerApi* | [**cadastrar**](docs/ProdutoControllerApi.md#cadastrar) | **POST** /produtos | 
*ProdutoControllerApi* | [**detalhar1**](docs/ProdutoControllerApi.md#detalhar1) | **GET** /produtos/{id} | 
*ProdutoControllerApi* | [**listar1**](docs/ProdutoControllerApi.md#listar1) | **GET** /produtos | 
*ProdutoControllerApi* | [**upload**](docs/ProdutoControllerApi.md#upload) | **POST** /produtos/foto/{id} | 
*UsuarioControllerApi* | [**atualizar**](docs/UsuarioControllerApi.md#atualizar) | **PUT** /usuarios | 
*UsuarioControllerApi* | [**detalhar**](docs/UsuarioControllerApi.md#detalhar) | **GET** /usuarios/{id} | 
*UsuarioControllerApi* | [**listar**](docs/UsuarioControllerApi.md#listar) | **GET** /usuarios | 

## Documentation for Models

 - [DadosAtualizacaoProduto](docs/DadosAtualizacaoProduto.md)
 - [DadosAtualizacaoUsuario](docs/DadosAtualizacaoUsuario.md)
 - [DadosAutenticacaoUsuario](docs/DadosAutenticacaoUsuario.md)
 - [DadosCadastroItem](docs/DadosCadastroItem.md)
 - [DadosCadastroPedido](docs/DadosCadastroPedido.md)
 - [DadosCadastroProduto](docs/DadosCadastroProduto.md)
 - [DadosCadastroProdutoEstoque](docs/DadosCadastroProdutoEstoque.md)
 - [DadosCadastroUsuario](docs/DadosCadastroUsuario.md)
 - [DadosDetalhamentoItem](docs/DadosDetalhamentoItem.md)
 - [DadosDetalhamentoPedido](docs/DadosDetalhamentoPedido.md)
 - [DadosDetalhamentoProduto](docs/DadosDetalhamentoProduto.md)
 - [DadosDetalhamentoUsuario](docs/DadosDetalhamentoUsuario.md)
 - [DadosMensagemGenerica](docs/DadosMensagemGenerica.md)
 - [FotoIdBody](docs/FotoIdBody.md)
 - [Pageable](docs/Pageable.md)


