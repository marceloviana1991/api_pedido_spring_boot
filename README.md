
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
*CadastrarControllerApi* | [**cadastrarUsuario**](docs/CadastrarControllerApi.md#cadastrarUsuario) | **POST** /cadastrar | 
*CadastrarControllerApi* | [**verificarCadastroDeUsuario**](docs/CadastrarControllerApi.md#verificarCadastroDeUsuario) | **GET** /cadastrar/{uuid} | 
*LoginControllerApi* | [**efetuarLogin**](docs/LoginControllerApi.md#efetuarLogin) | **POST** /login | 
*PedidoControllerApi* | [**cadastrarPedido**](docs/PedidoControllerApi.md#cadastrarPedido) | **POST** /pedidos | 
*PedidoControllerApi* | [**detalharPedido**](docs/PedidoControllerApi.md#detalharPedido) | **GET** /pedidos/{id} | 
*PedidoControllerApi* | [**excluirPedido**](docs/PedidoControllerApi.md#excluirPedido) | **DELETE** /pedidos/{id} | 
*PedidoControllerApi* | [**listarPedidos**](docs/PedidoControllerApi.md#listarPedidos) | **GET** /pedidos | 
*ProdutoControllerApi* | [**adicionarProdutoEmEstoque**](docs/ProdutoControllerApi.md#adicionarProdutoEmEstoque) | **POST** /produtos/estoque | 
*ProdutoControllerApi* | [**atualizarProduto**](docs/ProdutoControllerApi.md#atualizarProduto) | **PUT** /produtos | 
*ProdutoControllerApi* | [**cadastrarProduto**](docs/ProdutoControllerApi.md#cadastrarProduto) | **POST** /produtos | 
*ProdutoControllerApi* | [**detalharProduto**](docs/ProdutoControllerApi.md#detalharProduto) | **GET** /produtos/{id} | 
*ProdutoControllerApi* | [**listarProdutos**](docs/ProdutoControllerApi.md#listarProdutos) | **GET** /produtos | 
*ProdutoControllerApi* | [**uploadDeFotoDeProduto**](docs/ProdutoControllerApi.md#uploadDeFotoDeProduto) | **POST** /produtos/foto/{id} | 
*UsuarioControllerApi* | [**atualizarUsuario**](docs/UsuarioControllerApi.md#atualizarUsuario) | **PUT** /usuarios | 
*UsuarioControllerApi* | [**detalharUsuario**](docs/UsuarioControllerApi.md#detalharUsuario) | **GET** /usuarios/{id} | 
*UsuarioControllerApi* | [**listarUsuarios**](docs/UsuarioControllerApi.md#listarUsuarios) | **GET** /usuarios | 

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


