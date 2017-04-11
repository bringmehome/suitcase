/*
 * Copyright 2015 Junk Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.junkchen.blelib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunkChen on 2015/9/11 0011.
 * This class includes a small subset of standard GATT attributes for demonstration(表露) purposes.
 */
public abstract class GattAttributes {
    public static final Map<String, String> attributes = new HashMap<>();
    //GATT Services
    public static final String SERVICE_GENERIC_ACCESS = "00001800-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_GENERIC_ATTRIBUTE = "00001801-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_IMMEDIATE_ALERT = "00001802-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_LINK_LOSS = "00001803-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_TX_POWER = "00001804-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_CURRENT_TIME_SERVICE = "00001805-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_REFERENCE_TIME_UPDATE_SERVICE = "00001806-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_NEXT_DST_CHANGE_SERVICE = "00001807-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_GLUOSE = "00001808-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_HEALTH_THERMOMETER = "00001809-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_DEVICE_INFORMATION = "0000180A-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_HEART_RATE = "0000180D-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_PHONE_ALERT_STATUS_SERVICE = "0000180E-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_BATTERY_SERVICE = "0000180F-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_BLOOD_PRESSURE = "00001810-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_ALERT_NOTIFICATION_SERVICE = "00001811-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_HUMAN_INTERFACE_DEVICE = "00001812-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_SCAN_PARAMETERS = "00001813-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_RUNNING_SPEED_AND_CADENCE = "00001814-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_AUTOMATION_IO = "00001815-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_CYCLING_SPEED_AND_CADENCE = "00001816-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_CYCLING_POWER = "00001818-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_LOCATION_AND_NAVIGATION = "00001819-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_ENVIRONMENTAL_SENSING = "0000181A-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_BODY_COMPOSITION = "0000181B-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_USER_DATA = "0000181C-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_WEIGHT_SCALE = "0000181D-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_BOND_MANAGEMENT_SERVICE = "0000181E-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_CONTINUOUS_GLUCOSE_MONITORING = "0000181F-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_INTERNET_PROTOCOL_SUPPORT_SERVICE = "00001820-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_INDOOR_POSITIONING = "00001821-0000-1000-8000-00805F9B34FB";
    public static final String SERVICE_PULSE_OXIMETER_SERVICE = "00001822-0000-1000-8000-00805F9B34FB";

    //GATT Characteristics
    public static final String CHARACTERISTIC_CURRENT_TIME = "00002A2B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LOCAL_TIME_INFORMATION = "00002A0F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BATTERY_LEVEL = "00002A19-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BLOOD_PRESSURE_MEASUREMENT = "00002A35-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BLOOD_PRESSURE_FEATURE = "00002A49-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALERT_NOTIFICATION_CONTROL_POINT = "00002A44-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_NEW_ALERT = "00002A46-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SUPPORTED_NEW_ALERT_CATEGORY = "00002A47-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SUPPORT_UNREAD_ALERT_CATEGORY = "00002A48-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DIGITAL = "00002A56-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ANALOG = "00002A58-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_AGGREGATE = "00002A5A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CSC_MEASUREMENT = "00002A5B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CSC_FEATURE = "00002A5C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SC_CONTROL_POINT = "00002A55-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CYCLING_POWER_MEASUREMENT = "00002A63-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CYCLING_POWER_VECTOR = "00002A64-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CYCLING_POWER_FEATURE = "00002A65-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CYCLING_POWER_CONTROL_POINT = "00002A66-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SENSOR_LOCATION = "00002A5D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BODY_COMPOSITION_FEATURE = "00002A9B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BODY_COMPOSITION_MEASUREMENT = "00002A9C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BODY_SENSOR_LOCATION = "00002A38-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BOND_MANAGEMENT_CONTROL_POINT = "00002AA4-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BOND_MANAGEMENT_FEATURES = "00002AA5-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_MEASUREMENT = "00002AA7-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_FEATURE = "00002AA8-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_STATUS = "00002AA9-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_SESSION_START_TIME = "00002AAA-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_SESSION_RUN_TIME = "00002AAB-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RECORD_ACCESS_CONTROL_POINT = "00002A52-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CGM_SPECIFIC_OPS_CONTROL_POINT = "00002AAC-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_AEROBIC_HEART_RATE_LOWER_LIMIT = "00002A7E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_AEROBIC_HEART_RATE_UPPER_LIMIT = "00002A84-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_AEROBIC_THRESHOLD = "00002A7F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ANAEROBIC_HEART_RATE_LOWER_LIMIT = "00002A81-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ANAEROBIC_HEART_RATE_UPPER_LIMIT = "00002A82-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ANAEROBIC_THRESHOLD = "00002A83-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_AGE = "00002A80-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALERT_CATEGORY_ID = "00002A43-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALERT_CATEGORY_ID_BIT_MASK = "00002A42-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALERT_LEVEL = "00002A06-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALERT_STATUS = "00002A3F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ALTITUDE = "00002AB3-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_APPARENT_WIND_DIRECTION = "00002A73-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_APPARENT_WIND_SPEED = "00002A72-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_APPEARANCE = "00002A01-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BAROMETRIC_PRESSURE_TREND = "00002AA3-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BOOT_KEYBOARD_INPUT_REPORT = "00002A22-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BOOT_KEYBOARD_OUTPUT_REPORT = "00002A32-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_BOOT_MOUSE_INPUT_REPORT = "00002A33-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_CENTRAL_ADDRESS_RESOLUTION = "00002AA6-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DATABASE_CHANGE_INCREMENT = "00002A99-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DATE_OF_BIRTH = "00002A85-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DATE_OF_THRESHOLD_ASSESSMENT = "00002A86-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DATE_TIME = "00002A08-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DAY_DATE_TIME = "00002A0A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DAY_OF_WEEK = "00002A09-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DESCRIPTOR_VALUE_CHANGED = "00002A7D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DEVICE_NAME = "00002A00-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DEW_POINT = "00002A7B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_DST_OFFSET = "00002A0D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_ELEVATION = "00002A6C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_EMAIL_ADDRESS = "00002A87-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_EXACT_TIME_256 = "00002A0C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FAT_BURN_HEART_LOWER_LIMIT = "00002A88-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FAT_BURN_HEART_UPPER_LIMIT = "00002A89-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FIRMWARE_REVISION_STRING = "00002A26-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FIRST_NAME = "00002A8A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FIVE_ZONE_HEART_RATE_LIMITS = "00002A8B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_FLOOR_NUMBER  = "00002AB2-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_GENDER = "00002A8C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_GLUCOSE_FEATURE = "00002A51-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_GLUCOSE_MEASUREMENT = "00002A18-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_GLUCOSE_MEASUREMENT_CONTEXT = "00002A34-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_GUST_FACTOR = "00002A74-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HARDWARE_REVISION_STRING = "00002A27-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HEART_RATE_CONTROL_POINT = "00002A39-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HEART_RATE_MAX = "00002A8D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HEART_RATE_MEASUREMENT = "00002A37-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HEAT_INDEX = "00002A7A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HEIGHT = "00002A8E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HID_CONTROL_POINT = "00002A4C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HID_INFORMATION = "00002A4A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HIP_CIRCUMFERENCE = "00002A8F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_HUMIDITY = "00002A6F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_IEEE_11073_20601_REGULATORY_CERTIFICATION_DATA_LIST = "00002A2A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_INDOOR_POSITIONING_CONFIGURATION = "00002AAD-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_INTERMEDIATE_CUFF_PRESSURE = "00002A36-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_INTERMEDIATE_TEMPERATURE = "00002A1E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_IRRADIANCE = "00002A77-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LANGUAGE = "00002AA2-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LAST_NAME = "00002A90-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LATITUDE = "00002AAE-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LN_CONTROL_POINT = "00002A6B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LN_FEATURE = "00002A6A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LOCAL_NORTH_COORDINATE = "00002AB0-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LOCATION_AND_SPEED_CHARACTERISTIC = "00002A67-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LOCATION_NAME = "00002AB5-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_LONGITUDE = "00002AAF-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MAGNETIC_DECLINATION = "00002A2C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_2D= "00002AA0-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_3D= "00002AA1-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MANUFACTURER_NAME_STRING = "00002A29-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MAXIMUM_RECOMMENDED_HEART_RATE = "00002A91-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MEASUREMENT_INTERVAL = "00002A21-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_MODEL_NUMBER_STRING = "00002A24-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_NAVIGATION = "00002A68-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS = "00002A04-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PERIPHERAL_PRIVACY_FLAG = "00002A02-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PLX_CONTINUOUS_MEASUREMENT_CHARACTERISTIC = "00002A5F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PLX_FEATURES = "00002A60-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PLX_SPOT_CHECK_MEASUREMENT = "00002A5E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PNP_ID = "00002A50-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_POLLEN_CONCENTRATION = "00002A75-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_POSITION_QUALITY = "00002A69-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PRESSURE = "00002A6D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_PROTOCOL_MODE  = "00002A4E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RAINFALL = "00002A78-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RECONNECTION_ADDRESS  = "00002A03-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_REFERENCE_TIME_INFORMATION = "00002A14-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_REPORT = "00002A4D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_REPORT_MAP  = "00002A4B-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RESTING_HEART_RATE = "00002A92-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RINGER_CONTROL_POINT = "00002A40-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RINGER_SETTING = "00002A41-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RSC_FEATURE = "00002A54-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_RSC_MEASUREMENT = "00002A53-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SCAN_INTERVAL_WINDOW = "00002A4F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SCAN_REFRESH = "00002A31-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SERIAL_NUMBER_STRING = "00002A25-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SERVICE_CHANGED = "00002A05-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SOFTWARE_REVISION_STRING = "00002A28-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SPORT_TYPE_FOR_AEROBIC_AND_ANAEROBIC_THRESHOLDS = "00002A93-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_SYSTEM_ID = "00002A23-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TEMPERATURE = "00002A6E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TEMPERATURE_MEASUREMENT = "00002A1C-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TEMPERATURE_TYPE = "00002A1D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_THREE_ZONE_HEART_RATE_LIMITS = "00002A94-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_ACCURACY = "00002A12-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_SOURCE = "00002A13-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_UPDATE_CONTROL_POINT = "00002A16-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_UPDATE_STATE = "00002A17-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_WITH_DST = "00002A11-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TIME_ZONE = "00002A0E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TRUE_WIND_DIRECTION = "00002A71-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TRUE_WIND_SPEED = "00002A70-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TWO_ZONE_HEART_RATE_LIMIT = "00002A95-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_TX_POWER_LEVEL = "00002A07-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_UNCERTAINTY = "00002AB4-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_UNREAD_ALERT_STATUS= "00002A45-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_USER_CONTROL_POINT= "00002A9F-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_USER_INDEX= "00002A9A-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_UV_INDEX  = "00002A76-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_VO2_MAX  = "00002A96-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_WAIST_CIRCUMFERENCE = "00002A97-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_WEIGHT = "00002A98-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_WEIGHT_MEASUREMENT = "00002A9D-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_WEIGHT_SCALE_FEATURE = "00002A9E-0000-1000-8000-00805F9B34FB";
    public static final String CHARACTERISTIC_WIND_CHILL = "00002A79-0000-1000-8000-00805F9B34FB";

    //GATT Descriptors, Descriptors are defined attributes that describe a characteristic value.
    public static final String DESCRIPTOR_CHARACTERISTIC_EXTENDED_PROPERTIES =
            "00002900-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_CHARACTERISTIC_USER_DESCRIPTION =
            "00002901-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIGURATION =
            "00002902-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_SERVER_CHARACTERISTIC_CONFIGURATION =
            "00002903-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_CHARACTERISTIC_PREZENTATION_FORMAT =
            "00002904-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_CHARACTERISTIC_AGGREGATE_FORMAT =
            "00002905-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_VALID_RANGE =
            "00002906-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_EXTERNAL_REPORT_REFERENCE =
            "00002907-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_REPORT_REFERENCE =
            "00002908-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_NUMBER_OF_DIGITALS =
            "00002909-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_VALUE_TRIGGER_SETTING =
            "0000290A-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_ENVIRONMENTAL_SENSING_CONFIGURATION =
            "0000290B-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_ENVIRONMENTAL_SENSING_MEASUREMENT =
            "0000290C-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_ENVIRONMENTAL_SENSING_TRIGGER_SETTING =
            "0000290D-0000-1000-8000-00805F9B34FB";
    public static final String DESCRIPTOR_TIME_TRIGGER_SETTING =
            "0000290E-0000-1000-8000-00805F9B34FB";

    //Units
    public static final String UNIT_ = "00000000-0000-1000-8000-00805F9B34FB";
    public static final String UNIT_UNITLESS = "00002700-0000-1000-8000-00805F9B34FB";

    static {
        // Sample Services name.
        attributes.put(SERVICE_ALERT_NOTIFICATION_SERVICE, "Alert Notification Service");
        attributes.put(SERVICE_AUTOMATION_IO, "Automation IO");
        attributes.put(SERVICE_BATTERY_SERVICE, "Battery Service");
        attributes.put(SERVICE_BLOOD_PRESSURE, "Blood Pressure");
        attributes.put(SERVICE_BODY_COMPOSITION, "Body Composition");
        attributes.put(SERVICE_BOND_MANAGEMENT_SERVICE, "Bond Management Service");
        attributes.put(SERVICE_CONTINUOUS_GLUCOSE_MONITORING, "Continuous Glucose Monitoring");
        attributes.put(SERVICE_CURRENT_TIME_SERVICE, "Current Time Service");
        attributes.put(SERVICE_CYCLING_POWER, "Cycling Power");
        attributes.put(SERVICE_CYCLING_SPEED_AND_CADENCE, "Cycling Speed and Cadence");
        attributes.put(SERVICE_DEVICE_INFORMATION, "Device Information");
        attributes.put(SERVICE_ENVIRONMENTAL_SENSING, "Environmental Sensing");
        attributes.put(SERVICE_GENERIC_ACCESS, "Generic Access");
        attributes.put(SERVICE_GENERIC_ATTRIBUTE, "Generic Attribute");
        attributes.put(SERVICE_GLUOSE, "Glucose");
        attributes.put(SERVICE_HEALTH_THERMOMETER, "Health Thermometer");
        attributes.put(SERVICE_HEART_RATE, "Heart Rate");
        attributes.put(SERVICE_HUMAN_INTERFACE_DEVICE, "Human Interface Device");
        attributes.put(SERVICE_IMMEDIATE_ALERT, "Immediate Alert");
        attributes.put(SERVICE_INDOOR_POSITIONING, "Indoor Positioning");
        attributes.put(SERVICE_INTERNET_PROTOCOL_SUPPORT_SERVICE, "Internet Protocol Support Service");
        attributes.put(SERVICE_LINK_LOSS, "Link Loss");
        attributes.put(SERVICE_LOCATION_AND_NAVIGATION, "Location and Navigation");
        attributes.put(SERVICE_NEXT_DST_CHANGE_SERVICE, "Next DST Change Service");
        attributes.put(SERVICE_PHONE_ALERT_STATUS_SERVICE, "Phone Alert Status Service");
        attributes.put(SERVICE_PULSE_OXIMETER_SERVICE, "Pulse Oximeter Service");
        attributes.put(SERVICE_REFERENCE_TIME_UPDATE_SERVICE, "Reference Time Update Service");
        attributes.put(SERVICE_RUNNING_SPEED_AND_CADENCE, "Running Speed and Cadence");
        attributes.put(SERVICE_SCAN_PARAMETERS, "Scan Parameters");
        attributes.put(SERVICE_TX_POWER, "Tx Power");
        attributes.put(SERVICE_USER_DATA, "User Data");
        attributes.put(SERVICE_WEIGHT_SCALE, "Weight Scale");

        // Sample Characteristics name.
        attributes.put(CHARACTERISTIC_AEROBIC_HEART_RATE_LOWER_LIMIT, "Aerobic Heart Rate Lower Limit");
        attributes.put(CHARACTERISTIC_AEROBIC_HEART_RATE_UPPER_LIMIT, "Aerobic Heart Rate Upper Limit");
        attributes.put(CHARACTERISTIC_AEROBIC_THRESHOLD, "Aerobic Threshold");
        attributes.put(CHARACTERISTIC_AGE, "Age");
        attributes.put(CHARACTERISTIC_AGGREGATE, "Aggregate");
        attributes.put(CHARACTERISTIC_ALERT_CATEGORY_ID, "Alert Category ID");
        attributes.put(CHARACTERISTIC_ALERT_CATEGORY_ID_BIT_MASK, "Alert Category ID Bit Mask");
        attributes.put(CHARACTERISTIC_ALERT_LEVEL, "Alert Level");
        attributes.put(CHARACTERISTIC_ALERT_NOTIFICATION_CONTROL_POINT, "Alert Notification Control Point");
        attributes.put(CHARACTERISTIC_ALERT_STATUS, "Alert Status");
        attributes.put(CHARACTERISTIC_ALTITUDE, "Altitude");
        attributes.put(CHARACTERISTIC_ANAEROBIC_HEART_RATE_LOWER_LIMIT, "Anaerobic Heart Rate Lower Limit");
        attributes.put(CHARACTERISTIC_ANAEROBIC_HEART_RATE_UPPER_LIMIT, "Anaerobic Heart Rate Upper Limit");
        attributes.put(CHARACTERISTIC_ANAEROBIC_THRESHOLD, "Anaerobic Threshold");
        attributes.put(CHARACTERISTIC_ANALOG, "Analog");
        attributes.put(CHARACTERISTIC_APPARENT_WIND_DIRECTION, "Apparent Wind Direction");
        attributes.put(CHARACTERISTIC_APPARENT_WIND_SPEED, "Apparent Wind Speed");
        attributes.put(CHARACTERISTIC_APPEARANCE, "Appearance");
        attributes.put(CHARACTERISTIC_BAROMETRIC_PRESSURE_TREND, "Barometric Pressure Trend");
        attributes.put(CHARACTERISTIC_BATTERY_LEVEL, "Battery Level");
        attributes.put(CHARACTERISTIC_BLOOD_PRESSURE_FEATURE, "Blood Pressure Feature");
        attributes.put(CHARACTERISTIC_BLOOD_PRESSURE_MEASUREMENT, "Blood Pressure Measurement");
        attributes.put(CHARACTERISTIC_BODY_COMPOSITION_FEATURE, "Body Composition Feature");
        attributes.put(CHARACTERISTIC_BODY_COMPOSITION_MEASUREMENT, "Body Composition Measurement");
        attributes.put(CHARACTERISTIC_BODY_SENSOR_LOCATION, "Body Sensor Location");
        attributes.put(CHARACTERISTIC_BOND_MANAGEMENT_CONTROL_POINT, "Bond Management Control Point");
        attributes.put(CHARACTERISTIC_BOND_MANAGEMENT_FEATURES, "Bond Management Features");
        attributes.put(CHARACTERISTIC_BOOT_KEYBOARD_INPUT_REPORT, "Boot Keyboard Input Report");
        attributes.put(CHARACTERISTIC_BOOT_KEYBOARD_OUTPUT_REPORT, "Boot Keyboard Output Report");
        attributes.put(CHARACTERISTIC_BOOT_MOUSE_INPUT_REPORT, "Boot Mouse Input Report");
        attributes.put(CHARACTERISTIC_CENTRAL_ADDRESS_RESOLUTION, "Central Address Resolution");
        attributes.put(CHARACTERISTIC_CGM_FEATURE, "CGM Feature");
        attributes.put(CHARACTERISTIC_CGM_MEASUREMENT, "CGM Measurement");
        attributes.put(CHARACTERISTIC_CGM_SESSION_RUN_TIME, "CGM Session Run Time");
        attributes.put(CHARACTERISTIC_CGM_SESSION_START_TIME, "CGM Session Start Time");
        attributes.put(CHARACTERISTIC_CGM_SPECIFIC_OPS_CONTROL_POINT, "CGM Specific Ops Control Point");
        attributes.put(CHARACTERISTIC_CGM_STATUS, "CGM Status");
        attributes.put(CHARACTERISTIC_CSC_FEATURE, "CSC Feature");
        attributes.put(CHARACTERISTIC_CSC_MEASUREMENT, "CSC Measurement");
        attributes.put(CHARACTERISTIC_CURRENT_TIME, "Current Time");
        attributes.put(CHARACTERISTIC_CYCLING_POWER_CONTROL_POINT, "Cycling Power Control Point");
        attributes.put(CHARACTERISTIC_CYCLING_POWER_FEATURE, "Cycling Power Feature");
        attributes.put(CHARACTERISTIC_CYCLING_POWER_MEASUREMENT, "Cycling Power Measurement");
        attributes.put(CHARACTERISTIC_CYCLING_POWER_VECTOR, "Cycling Power Vector");
        attributes.put(CHARACTERISTIC_DATABASE_CHANGE_INCREMENT, "Database Change Increment");
        attributes.put(CHARACTERISTIC_DATE_OF_BIRTH, "Date of Birth");
        attributes.put(CHARACTERISTIC_DATE_OF_THRESHOLD_ASSESSMENT, "Date of Threshold Assessment");
        attributes.put(CHARACTERISTIC_DATE_TIME, "Date Time");
        attributes.put(CHARACTERISTIC_DAY_DATE_TIME, "Day Date Time");
        attributes.put(CHARACTERISTIC_DAY_OF_WEEK, "Day of Week");
        attributes.put(CHARACTERISTIC_DESCRIPTOR_VALUE_CHANGED, "Descriptor Value Changed");
        attributes.put(CHARACTERISTIC_DEVICE_NAME, "Device Name");
        attributes.put(CHARACTERISTIC_DEW_POINT, "Dew Point");
        attributes.put(CHARACTERISTIC_DIGITAL, "Digital");
        attributes.put(CHARACTERISTIC_DST_OFFSET, "DST Offset");
        attributes.put(CHARACTERISTIC_ELEVATION, "Elevation");
        attributes.put(CHARACTERISTIC_EMAIL_ADDRESS, "Email Address");
        attributes.put(CHARACTERISTIC_EXACT_TIME_256, "Exact Time 256");
        attributes.put(CHARACTERISTIC_FAT_BURN_HEART_LOWER_LIMIT, "Fat Burn Heart Rate Lower Limit");
        attributes.put(CHARACTERISTIC_FAT_BURN_HEART_UPPER_LIMIT, "Fat Burn Heart Rate Upper Limit");
        attributes.put(CHARACTERISTIC_FIRMWARE_REVISION_STRING, "Firmware Revision String");
        attributes.put(CHARACTERISTIC_FIRST_NAME, "First Name");
        attributes.put(CHARACTERISTIC_FIVE_ZONE_HEART_RATE_LIMITS, "Five Zone Heart Rate Limits");
        attributes.put(CHARACTERISTIC_FLOOR_NUMBER, "Floor Number");
        attributes.put(CHARACTERISTIC_GENDER, "Gender");
        attributes.put(CHARACTERISTIC_GLUCOSE_FEATURE, "Glucose Feature");
        attributes.put(CHARACTERISTIC_GLUCOSE_MEASUREMENT, "Glucose Measurement");
        attributes.put(CHARACTERISTIC_GLUCOSE_MEASUREMENT_CONTEXT, "Glucose Measurement Context");
        attributes.put(CHARACTERISTIC_GUST_FACTOR, "Gust Factor");
        attributes.put(CHARACTERISTIC_HARDWARE_REVISION_STRING, "Hardware Revision String");
        attributes.put(CHARACTERISTIC_HEART_RATE_CONTROL_POINT, "Heart Rate Control Point");
        attributes.put(CHARACTERISTIC_HEART_RATE_MAX, "Heart Rate Max");
        attributes.put(CHARACTERISTIC_HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        attributes.put(CHARACTERISTIC_HEAT_INDEX, "Heat Index");
        attributes.put(CHARACTERISTIC_HEIGHT, "Height");
        attributes.put(CHARACTERISTIC_HID_CONTROL_POINT, "HID Control Point");
        attributes.put(CHARACTERISTIC_HID_INFORMATION, "HID Information");
        attributes.put(CHARACTERISTIC_HIP_CIRCUMFERENCE, "Hip Circumference");
        attributes.put(CHARACTERISTIC_HUMIDITY, "Humidity");
        attributes.put(CHARACTERISTIC_IEEE_11073_20601_REGULATORY_CERTIFICATION_DATA_LIST, "IEEE 11073-20601 Regulatory Certification Data List");
        attributes.put(CHARACTERISTIC_INDOOR_POSITIONING_CONFIGURATION, "Indoor Positioning Configuration");
        attributes.put(CHARACTERISTIC_INTERMEDIATE_CUFF_PRESSURE, "Intermediate Cuff Pressure");
        attributes.put(CHARACTERISTIC_INTERMEDIATE_TEMPERATURE, "Intermediate Temperature");
        attributes.put(CHARACTERISTIC_IRRADIANCE, "Irradiance");
        attributes.put(CHARACTERISTIC_LANGUAGE, "Language");
        attributes.put(CHARACTERISTIC_LAST_NAME, "Last Name");
        attributes.put(CHARACTERISTIC_LATITUDE, "Latitude");
        attributes.put(CHARACTERISTIC_LN_CONTROL_POINT, "LN Control Point");
        attributes.put(CHARACTERISTIC_LN_FEATURE, "LN Feature");
        attributes.put(CHARACTERISTIC_LOCAL_NORTH_COORDINATE, "Local North Coordinate");
        attributes.put(CHARACTERISTIC_LOCAL_TIME_INFORMATION, "Local Time Information");
        attributes.put(CHARACTERISTIC_LOCATION_AND_SPEED_CHARACTERISTIC, "Location and Speed Characteristic");
        attributes.put(CHARACTERISTIC_LOCATION_NAME, "Location Name");
        attributes.put(CHARACTERISTIC_LONGITUDE, "Longitude");
        attributes.put(CHARACTERISTIC_MAGNETIC_DECLINATION, "Magnetic Declination");
        attributes.put(CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_2D, "Magnetic Flux Density - 2D");
        attributes.put(CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_3D, "Magnetic Flux Density - 3D");
        attributes.put(CHARACTERISTIC_MANUFACTURER_NAME_STRING, "Manufacturer Name String");
        attributes.put(CHARACTERISTIC_MAXIMUM_RECOMMENDED_HEART_RATE, "Maximum Recommended Heart Rate");
        attributes.put(CHARACTERISTIC_MEASUREMENT_INTERVAL, "Measurement Interval");
        attributes.put(CHARACTERISTIC_MODEL_NUMBER_STRING, "Model Number String");
        attributes.put(CHARACTERISTIC_NAVIGATION, "Navigation");
        attributes.put(CHARACTERISTIC_NEW_ALERT, "New Alert");
        attributes.put(CHARACTERISTIC_PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS, "Peripheral Preferred Connection Parameters");
        attributes.put(CHARACTERISTIC_PERIPHERAL_PRIVACY_FLAG, "Peripheral Privacy Flag");
        attributes.put(CHARACTERISTIC_PLX_CONTINUOUS_MEASUREMENT_CHARACTERISTIC, "PLX Continuous Measurement Characteristic");
        attributes.put(CHARACTERISTIC_PLX_FEATURES, "PLX Features");
        attributes.put(CHARACTERISTIC_PLX_SPOT_CHECK_MEASUREMENT, "PLX Spot-Check Measurement");
        attributes.put(CHARACTERISTIC_PNP_ID, "PnP ID");
        attributes.put(CHARACTERISTIC_POLLEN_CONCENTRATION, "Pollen Concentration");
        attributes.put(CHARACTERISTIC_POSITION_QUALITY, "Position Quality");
        attributes.put(CHARACTERISTIC_PRESSURE, "Pressure");
        attributes.put(CHARACTERISTIC_PROTOCOL_MODE, "Protocol Mode");
        attributes.put(CHARACTERISTIC_RAINFALL, "Rainfall");
        attributes.put(CHARACTERISTIC_RECONNECTION_ADDRESS, "Reconnection Address");
        attributes.put(CHARACTERISTIC_RECORD_ACCESS_CONTROL_POINT, "Record Access Control Point");
        attributes.put(CHARACTERISTIC_REFERENCE_TIME_INFORMATION, "Reference Time Information");
        attributes.put(CHARACTERISTIC_REPORT, "Report");
        attributes.put(CHARACTERISTIC_REPORT_MAP, "Report Map");
        attributes.put(CHARACTERISTIC_RESTING_HEART_RATE, "Resting Heart Rate");
        attributes.put(CHARACTERISTIC_RINGER_CONTROL_POINT, "Ringer Control point");
        attributes.put(CHARACTERISTIC_RINGER_SETTING, "Ringer Setting");
        attributes.put(CHARACTERISTIC_RSC_FEATURE, "RSC Feature");
        attributes.put(CHARACTERISTIC_RSC_MEASUREMENT, "RSC Measurement");
        attributes.put(CHARACTERISTIC_SC_CONTROL_POINT, "SC Control Point");
        attributes.put(CHARACTERISTIC_SCAN_INTERVAL_WINDOW, "Scan Interval Window");
        attributes.put(CHARACTERISTIC_SCAN_REFRESH, "Scan Refresh");
        attributes.put(CHARACTERISTIC_SENSOR_LOCATION, "Sensor Location");
        attributes.put(CHARACTERISTIC_SERIAL_NUMBER_STRING, "Serial Number String");
        attributes.put(CHARACTERISTIC_SERVICE_CHANGED, "Service Changed");
        attributes.put(CHARACTERISTIC_SOFTWARE_REVISION_STRING, "Software Revision String");
        attributes.put(CHARACTERISTIC_SPORT_TYPE_FOR_AEROBIC_AND_ANAEROBIC_THRESHOLDS, "Sport Type for Aerobic and Anaerobic Thresholds");
        attributes.put(CHARACTERISTIC_SUPPORTED_NEW_ALERT_CATEGORY, "Supported New Alert Category");
        attributes.put(CHARACTERISTIC_SUPPORT_UNREAD_ALERT_CATEGORY, "Supported Unread Alert Category");
        attributes.put(CHARACTERISTIC_SYSTEM_ID, "System ID");
        attributes.put(CHARACTERISTIC_TEMPERATURE, "Temperature");
        attributes.put(CHARACTERISTIC_TEMPERATURE_MEASUREMENT, "Temperature Measurement");
        attributes.put(CHARACTERISTIC_TEMPERATURE_TYPE, "Temperature Type");
        attributes.put(CHARACTERISTIC_THREE_ZONE_HEART_RATE_LIMITS, "Three Zone Heart Rate Limits");
        attributes.put(CHARACTERISTIC_TIME_ACCURACY, "Time Accuracy");
        attributes.put(CHARACTERISTIC_TIME_SOURCE, "Time Source");
        attributes.put(CHARACTERISTIC_TIME_UPDATE_CONTROL_POINT, "Time Update Control Point");
        attributes.put(CHARACTERISTIC_TIME_UPDATE_STATE, "Time Update State");
        attributes.put(CHARACTERISTIC_TIME_WITH_DST, "Time with DST");
        attributes.put(CHARACTERISTIC_TIME_ZONE, "Time Zone");
        attributes.put(CHARACTERISTIC_TRUE_WIND_DIRECTION, "True Wind Direction");
        attributes.put(CHARACTERISTIC_TRUE_WIND_SPEED, "True Wind Speed");
        attributes.put(CHARACTERISTIC_TWO_ZONE_HEART_RATE_LIMIT, "Two Zone Heart Rate Limit");
        attributes.put(CHARACTERISTIC_TX_POWER_LEVEL, "Tx Power Level");
        attributes.put(CHARACTERISTIC_UNCERTAINTY, "Uncertainty");
        attributes.put(CHARACTERISTIC_UNREAD_ALERT_STATUS, "Unread Alert Status");
        attributes.put(CHARACTERISTIC_USER_CONTROL_POINT, "User Control Point");
        attributes.put(CHARACTERISTIC_USER_INDEX, "User Index");
        attributes.put(CHARACTERISTIC_UV_INDEX, "UV Index");
        attributes.put(CHARACTERISTIC_VO2_MAX, "VO2 Max");
        attributes.put(CHARACTERISTIC_WAIST_CIRCUMFERENCE, "Waist Circumference");
        attributes.put(CHARACTERISTIC_WEIGHT, "Weight");
        attributes.put(CHARACTERISTIC_WEIGHT_MEASUREMENT, "Weight Measurement");
        attributes.put(CHARACTERISTIC_WEIGHT_SCALE_FEATURE, "Weight Scale Feature");
        attributes.put(CHARACTERISTIC_WIND_CHILL, "Wind Chill");

        // Sample descriptors name.
        attributes.put(DESCRIPTOR_CHARACTERISTIC_EXTENDED_PROPERTIES, "Characteristic Extended Properties");
        attributes.put(DESCRIPTOR_CHARACTERISTIC_USER_DESCRIPTION, "Characteristic User Description");
        attributes.put(DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIGURATION, "Client Characteristic Configuration");
        attributes.put(DESCRIPTOR_SERVER_CHARACTERISTIC_CONFIGURATION, "Server Characteristic Configuration");
        attributes.put(DESCRIPTOR_CHARACTERISTIC_PREZENTATION_FORMAT, "Characteristic Presentation Format");
        attributes.put(DESCRIPTOR_CHARACTERISTIC_AGGREGATE_FORMAT, "Characteristic Aggregate Format");
        attributes.put(DESCRIPTOR_VALID_RANGE, "Valid Range");
        attributes.put(DESCRIPTOR_EXTERNAL_REPORT_REFERENCE, "External Report Reference");
        attributes.put(DESCRIPTOR_REPORT_REFERENCE, "Report Reference");
        attributes.put(DESCRIPTOR_NUMBER_OF_DIGITALS, "Number of Digitals");
        attributes.put(DESCRIPTOR_VALUE_TRIGGER_SETTING, "Value Trigger Setting");
        attributes.put(DESCRIPTOR_ENVIRONMENTAL_SENSING_CONFIGURATION, "Environmental Sensing Configuration");
        attributes.put(DESCRIPTOR_ENVIRONMENTAL_SENSING_MEASUREMENT, "Environmental Sensing Measurement");
        attributes.put(DESCRIPTOR_ENVIRONMENTAL_SENSING_TRIGGER_SETTING, "Environmental Sensing Trigger Setting");
        attributes.put(DESCRIPTOR_TIME_TRIGGER_SETTING, "Time Trigger Setting");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid.toUpperCase());
        return name == null ? defaultName : name;
    }

}
