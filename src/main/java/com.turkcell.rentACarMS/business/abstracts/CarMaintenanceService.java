package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CarMaintenanceDto;
import com.turkcell.rentACarMS.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CarMaintenanceService {
    DataResult<List<ListCarMaintenanceDto>> listAll();
    Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest);
    Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
    Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
    DataResult<CarMaintenanceDto> getById(int carMaintenanceId);
}
