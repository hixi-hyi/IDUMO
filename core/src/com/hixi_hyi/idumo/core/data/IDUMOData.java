package com.hixi_hyi.idumo.core.data;

import com.hixi_hyi.idumo.core.data.raw.IDUMODataTypeRaw;

public interface IDUMOData {

	IDUMODataTypeRaw get(String name);

}
