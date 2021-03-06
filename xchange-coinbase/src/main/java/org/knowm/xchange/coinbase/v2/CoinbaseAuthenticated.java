package org.knowm.xchange.coinbase.v2;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.coinbase.v2.dto.CoinbaseException;
import org.knowm.xchange.coinbase.v2.dto.account.CoinbaseAccountData;
import org.knowm.xchange.coinbase.v2.dto.account.CoinbaseAccountsData;
import org.knowm.xchange.coinbase.v2.dto.account.CoinbaseBuyData;
import org.knowm.xchange.coinbase.v2.dto.account.CoinbasePaymentMethodsData;
import org.knowm.xchange.coinbase.v2.dto.account.CoinbaseSellData;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface CoinbaseAuthenticated extends Coinbase {

  /**
   * All API key requests must be signed and contain the following headers. 
   * 
   * All request bodies should have content type application/json and be valid JSON.
   * 
   * The CB-ACCESS-SIGN header is generated by creating a sha256 HMAC using the secret key on the prehash string timestamp + method + requestPath + body (where + represents string concatenation). 
   * The timestamp value is the same as the CB-ACCESS-TIMESTAMP header.
   * 
   * The body is the request body string or omitted if there is no request body (typically for GET requests).
   * 
   * The method should be UPPER CASE.
   * 
   * <a href="https://developers.coinbase.com/api/v2#api-key">developers.coinbase.com/api/v2#api-key</a>
   */
  final String CB_ACCESS_KEY = "CB-ACCESS-KEY";
  final String CB_ACCESS_SIGN = "CB-ACCESS-SIGN";
  final String CB_ACCESS_TIMESTAMP = "CB-ACCESS-TIMESTAMP";

  final String CONTENT_TYPE = "Content-Type";

  @GET
  @Path("accounts")
  CoinbaseAccountsData getAccounts(@HeaderParam(CB_VERSION) String apiVersion, @HeaderParam(CB_ACCESS_KEY) String apiKey, @HeaderParam(CB_ACCESS_SIGN) String signature,
      @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp) throws IOException, CoinbaseException;

  @GET
  @Path("accounts/{currency}")
  CoinbaseAccountData getAccount(@HeaderParam(CB_VERSION) String apiVersion, @HeaderParam(CB_ACCESS_KEY) String apiKey, @HeaderParam(CB_ACCESS_SIGN) String signature,
      @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp, @PathParam("currency") String currency) throws IOException, CoinbaseException;

  @POST
  @Path("accounts")
  @Consumes(MediaType.APPLICATION_JSON)
  CoinbaseAccountData createAccount(@HeaderParam(CONTENT_TYPE) String contentType, @HeaderParam(CB_VERSION) String apiVersion,
      @HeaderParam(CB_ACCESS_KEY) String apiKey, @HeaderParam(CB_ACCESS_SIGN) String signature, @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp, Object payload)
      throws IOException, CoinbaseException;

  @GET
  @Path("payment-methods")
  CoinbasePaymentMethodsData getPaymentMethods(@HeaderParam(CB_VERSION) String apiVersion, @HeaderParam(CB_ACCESS_KEY) String apiKey,
      @HeaderParam(CB_ACCESS_SIGN) String signature, @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp) throws IOException, CoinbaseException;

  @POST
  @Path("accounts/{account}/buys")
  @Consumes(MediaType.APPLICATION_JSON)
  CoinbaseBuyData buy(@HeaderParam(CONTENT_TYPE) String contentType, @HeaderParam(CB_VERSION) String apiVersion, @HeaderParam(CB_ACCESS_KEY) String apiKey,
      @HeaderParam(CB_ACCESS_SIGN) String signature, @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp, @PathParam("account") String accountId, Object payload)
      throws IOException, CoinbaseException;

  @POST
  @Path("accounts/{account}/sells")
  @Consumes(MediaType.APPLICATION_JSON)
  CoinbaseSellData sell(@HeaderParam(CONTENT_TYPE) String contentType, @HeaderParam(CB_VERSION) String apiVersion, @HeaderParam(CB_ACCESS_KEY) String apiKey,
      @HeaderParam(CB_ACCESS_SIGN) String signature, @HeaderParam(CB_ACCESS_TIMESTAMP) BigDecimal timestamp, @PathParam("account") String accountId, Object payload)
      throws IOException, CoinbaseException;
}
