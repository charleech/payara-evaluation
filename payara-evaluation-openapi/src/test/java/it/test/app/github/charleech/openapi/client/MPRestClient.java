/**
 * Copyright (c) 2019-2019 By Charlee Chitsuk
 *
 * All rights reserved. No part of this program and the accompanying
 * materials may be reproduced, distributed, or transmitted in
 * any form or by any means, including photocopying, recording, or
 * other electronic or mechanical methods, without the prior written
 * permission of the publisher, except in the case of brief quotations
 * embodied in critical reviews and certain other noncommercial uses
 * permitted by copyright law. For permission requests, write to the
 * publisher, addressed "Attention: Permissions Coordinator," at the
 * address below.
 *
 * Charlee Chitsuk
 *
 * Summit Computer Co.,Ltd.
 * 109 C.C.T. Building, 12th Floor,
 * Surawong Road, Suriyawong,
 * Bangrak, Bangkok,
 * Thailand. 10500.
 *
 * Tel. 66-2-237-6922 to 3, 66-2-238-0895 to 9
 * Fax: 66-2-236-7392
 * email: charlee@summitthai.com
 * website: http://www.summitthai.com
 */
package it.test.app.github.charleech.openapi.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * <p>
 * This is an interface which describes the feature as a {@code REST client}
 * for the common {@code microprofile}.
 * </p>
 * <p>
 * It is registered at
 * {@code /src/test/resources/META-INF/microprofile-config.properties}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see RegisterRestClient
 */
@ApplicationScoped
@RegisterRestClient
@Consumes({
    MediaType.APPLICATION_JSON
})
@Produces({
    MediaType.APPLICATION_JSON
})
public interface MPRestClient {

    /**
     * Get OpenAPI.
     *
     * @return The OpenAPI
     */
    @GET
    @Path("/openapi")
    String getOpenAPI();
}
