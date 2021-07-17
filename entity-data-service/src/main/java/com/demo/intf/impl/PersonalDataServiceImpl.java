package com.demo.intf.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Service;

import com.demo.file.helper.ExcelFileReader;
import com.demo.intf.IPersonalDataService;
import com.demo.model.AddressDataModel;
import com.demo.model.PersonalDataModel;
import com.demo.util.ApplicationConstant;
import com.demo.util.FileResourceUtility;

@Service
public class PersonalDataServiceImpl implements IPersonalDataService {


	@Override
	public PersonalDataModel getPersonalData(String uid) {


		StringBuilder dataString = ExcelFileReader.readExcelSingleRow(
				FileResourceUtility.getFileDetails(ApplicationConstant.PERSONAL_FILE_TYPE), uid);

		if(dataString == null)
			return null;

		return getPersonalDataModelFromDataString(dataString.toString(), uid);
	}

	@Override
	public Set<AddressDataModel> getAddressList(String uid) {
		StringBuilder dataString = ExcelFileReader.readExcelMultipleRow(
				FileResourceUtility.getFileDetails(ApplicationConstant.ADDRESS_FILE_TYPE), uid);

		if(dataString == null)
			return null;

		return getAddressDataModelFromDataString(dataString.toString(), uid);
	}

	private PersonalDataModel getPersonalDataModelFromDataString(String dataString, String uid) {
		PersonalDataModel retObj = new PersonalDataModel(uid);
		int index = 0;
		System.out.println("DataString:"+dataString);
		for(String data:dataString.split(ApplicationConstant.DATA_DELIMETER)) {
			System.out.println(index+":data:"+data);
			switch(index) {
			case 1:
				retObj.setFirstName(data);
				break;
			case 2:
				retObj.setLastName(data);
				break;
			case 3:				
				retObj.setDob(DateUtil.getJavaDate(Double.parseDouble(data)));
				break;
			case 4:
				retObj.setBirthPlaceCode(new BigDecimal(data).intValue());
				break;
			case 5:
				retObj.setCitizenshipCode(new BigDecimal(data).intValue());
				break;
			case 6:
				retObj.setGenderCode(new BigDecimal(data).intValue());
				break;
			case 7:
				retObj.setMaritalCode(new BigDecimal(data).intValue());
				break;
			default:

				break;

			}
			index++;
		}

		return retObj;
	}

	private Set<AddressDataModel> getAddressDataModelFromDataString(String dataString, String uid) {
		Set<AddressDataModel> retSet = new LinkedHashSet<>();
		
		System.out.println("DataString:"+dataString);
		for(String subString:dataString.split(ApplicationConstant.ROW_DELIMETER)) {
			AddressDataModel address = new AddressDataModel(uid);			
			System.out.println("subString:"+subString);
			int index = 0;
			for(String data:subString.split(ApplicationConstant.DATA_DELIMETER)) {
				System.out.println(index+":data:"+data);
				switch(index) {
				case 0:
					address.setsNo(new BigDecimal(data).intValue());
					break;				
				case 2:
					address.setAddressType(new BigDecimal(data).intValue());
					break;
				case 3:				
					address.setFlatNumber(data);
					break;
				case 4:
					address.setBuildingName(data);
					break;
				case 5:
					address.setStreet(data);							
					break;
				case 6:
					address.setArea(data);
					break;
				case 7:
					address.setCity(new BigDecimal(data).intValue());
					break;
				case 8:
					address.setState(new BigDecimal(data).intValue());
					break;
				case 9:
					address.setPinCode(new BigDecimal(data).longValue());
					break;
				default:

					break;

				}
				index++;
			}
			retSet.add(address);
		}

		return retSet;
	}
}
