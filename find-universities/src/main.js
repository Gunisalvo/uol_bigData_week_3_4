'use strict'

const http = require('sync-request')
const cheerio = require('cheerio')
const regions = require('country-data').regions
const csv = require('csv')

let defaultBatchSize = 50

let countryCodes = ['EDU', 'UK'] //usa, uk

for(let key in regions){
    if(['westernEurope',
        'northernAmerica',
        'easternEurope',
        'southernEurope',
        'northernEurope'].indexOf(key) > -1){
        countryCodes = countryCodes.concat(regions[key].countries)
    }
}

let buildUrl = (country, iteration) => {
    let position = (iteration * defaultBatchSize) + 1
    return 'http://univ.cc/search.php?dom=' + country.toLowerCase() + '&key=&start=' + position
}

let queryHttp = (country, iteration) => {
    let response = http('GET', buildUrl(country, iteration))
    let wrappedHtml = cheerio.load(response.getBody())
    return wrappedHtml
}

let extractChunck = (data) => {
    let chunck = []
    data('ol li a').each((index, element) => {
        chunck.push(element.children[0].data)
    })
    return chunck
}

let output = csv().to("usa-euro-unis.csv");

for(let index in countryCodes){
    let iteration = 0
    let done = false
    let countryInstitutions = []
    let code = countryCodes[index]
    while((countryInstitutions.length % defaultBatchSize) == 0){
        console.log( code + ' - ' + countryInstitutions.length + ' ' + iteration)
        let result = extractChunck(queryHttp(countryCodes[index], iteration))
        if(!result[0] || countryInstitutions.indexOf(result[0]) > -1){
            countryInstitutions = countryInstitutions.concat(['done!'])
        }else{
            countryInstitutions = countryInstitutions.concat(result)
        }
        iteration += 1
    }
    for(let iIndex in countryInstitutions){
        let locationCode
        if(code == 'EDU' || regions['northernAmerica'].countries.indexOf(code) > -1){
            locationCode = 'North America'
        }else{
            locationCode = 'Europe'
        }
        output.write([locationCode, countryInstitutions[iIndex]])
    }
}

output.end()