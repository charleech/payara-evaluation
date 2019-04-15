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
package test.app.github.charleech.base.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * This is a concrete implementing class which provides the features as a test
 * suite for unit testing the customized {@code microprofile: fault tolerance}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
    DataBeanTester.class
})
public class DataBeanTestSuite {

}
