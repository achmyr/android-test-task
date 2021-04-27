package uk.acm64.openweather.feature.pollution.data.model

import uk.acm64.openweather.feature.pollution.domain.model.PollutionInformation


data class PollutionHistoryResponse(val coord: List<Int>, val pollutionList: List<PollutionData>) {
    fun toData(): List<PollutionInformation> = listOf()
}

data class PollutionData(val dt:Long, val components: PollutionComponents)

data class PollutionComponents(val co: Float)

//"""
//{
//  "coord":[
//    50,
//    50
//  ],
//  "list":[
//    {
//      "dt":1605182400,
//      "main":{
//        "aqi":1
//      },
//      "components":{
//        "co":201.94053649902344,
//        "no":0.01877197064459324,
//        "no2":0.7711350917816162,
//        "o3":68.66455078125,
//        "so2":0.6407499313354492,
//        "pm2_5":0.5,
//        "pm10":0.540438711643219,
//        "nh3":0.12369127571582794
//      }
//    }
//  ]
//} """