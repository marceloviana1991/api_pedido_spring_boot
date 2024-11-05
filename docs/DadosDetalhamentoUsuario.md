# DadosDetalhamentoUsuario

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** |  |  [optional]
**login** | **String** |  |  [optional]
**email** | **String** |  |  [optional]
**tipoUsuario** | [**TipoUsuarioEnum**](#TipoUsuarioEnum) |  |  [optional]
**situacaoUsuario** | [**SituacaoUsuarioEnum**](#SituacaoUsuarioEnum) |  |  [optional]

<a name="TipoUsuarioEnum"></a>
## Enum: TipoUsuarioEnum
Name | Value
---- | -----
USER | &quot;USER&quot;
ADMIN | &quot;ADMIN&quot;

<a name="SituacaoUsuarioEnum"></a>
## Enum: SituacaoUsuarioEnum
Name | Value
---- | -----
PENDENTE | &quot;PENDENTE&quot;
ATIVO | &quot;ATIVO&quot;
