# certificate-info

Plugin to get SSL certificate information.

## Install

```bash
npm install certificate-info
npx cap sync
```

## API

<docgen-index>

* [`getExpirationDate(...)`](#getexpirationdate)
* [`getExpirationDates()`](#getexpirationdates)
* [`checkValidation(...)`](#checkvalidation)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getExpirationDate(...)

```typescript
getExpirationDate(params: CertFilename) => Promise<CertExpiration>
```

Get the expiration date of the certification file.

| Param        | Type                                                  | Description |
| ------------ | ----------------------------------------------------- | ----------- |
| **`params`** | <code><a href="#certfilename">CertFilename</a></code> | CertFile    |

**Returns:** <code>Promise&lt;<a href="#certexpiration">CertExpiration</a>&gt;</code>

--------------------


### getExpirationDates()

```typescript
getExpirationDates() => Promise<CertExpirations>
```

Get the expiration date of each file in the certificate directory.

**Returns:** <code>Promise&lt;<a href="#certexpirations">CertExpirations</a>&gt;</code>

--------------------


### checkValidation(...)

```typescript
checkValidation(params?: CertDate | undefined) => Promise<CertValid>
```

Check if any certificate in directory is valid compared to today. If param is define, check each certificate with the given date.

| Param        | Type                                          | Description                                   |
| ------------ | --------------------------------------------- | --------------------------------------------- |
| **`params`** | <code><a href="#certdate">CertDate</a></code> | <a href="#certdate">CertDate</a> \| undefined |

**Returns:** <code>Promise&lt;<a href="#certvalid">CertValid</a>&gt;</code>

--------------------


### Type Aliases


#### CertExpiration

<code>{ /** * Expiration date in ISO 8601 format */ expirationDate:string; }</code>


#### CertFilename

<code>{ /** * Name of the certification file */ filename: string; }</code>


#### CertExpirations

<code>{ /** * List of expiration dates in ISO 8601 format */ expirationDates: string []; }</code>


#### CertValid

<code>{ /** * Status of the certificate */ valid: boolean; }</code>


#### CertDate

<code>{ /** * Date in format ISO 8601 */ date:string; }</code>

</docgen-api>
