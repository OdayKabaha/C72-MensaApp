/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.dataprovider;

import java.io.IOException;

public interface OpenMensaDataProvider {

    String queryAPI(String query) throws IOException;
}
