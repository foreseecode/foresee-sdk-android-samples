var express = require("express");
const AWS = require('aws-sdk');
const json2html = require('node-json2html');
const { WellArchitected } = require("aws-sdk");
const bucket = "downtime-json";
var responseFromS3Bucket = "";

let parsedS3Array = [];
let parsedS3ArrayData = [];

let s3 = new AWS.S3({
    region: 'us-east-1',
    accessKeyId: 'AKIA2VFA52HCFF2DJBEA',
    secretAccessKey: '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'
});


var router = express.Router();

router.get("/", async function (req, res, next) {
    console.log("Index page")
    let s3Response;

    var params = {
        Bucket: "downtime-json",
        MaxKeys: 10000
    };

    s3.listObjects(params, function (err, data) {
        if (err) console.log(err, err.stack); // an error occurred
        else //console.log(data);
        var jsonData = JSON.parse(JSON.stringify(data));
        for (var i = 0; i < jsonData.Contents.length; i++) {
            var content = jsonData.Contents[i];
            //console.log(content.Key);
            var response = new String(content.Key);
            response = response.replace(".json", "").replace(" ", "~");
            console.log("response: "+response);
            var splitResponse = response.split('_');

            const s3SplitFileObject = new Object();
            s3SplitFileObject.userId = splitResponse[0];
            s3SplitFileObject.deviceType = splitResponse[1];
            s3SplitFileObject.deviceVersion = splitResponse[2];
            s3SplitFileObject.country = splitResponse[3];
            s3SplitFileObject.latitude = splitResponse[4];
            s3SplitFileObject.longitude = splitResponse[5];
            s3SplitFileObject.exception = splitResponse[6];
            s3SplitFileObject.timestamp = splitResponse[7];


            parsedS3Array.push(s3SplitFileObject)

        }

        res.render("index", { s3data: JSON.stringify(parsedS3Array) });
    });

    //console.log("Response is: " + responseFromS3Bucket);




    //working 

    //const myObject = await getObject('downtime-json', 'usr109_android_9_480_456_1669911289867.json');
    //res.render("index", {s3data:myObject, device:"Android"})

});

router.get("/users", async (req, res, next) => {
    //res.render("users");
    console.log("URL: "+req.url)


    const url = new URL(
        "http://localhost:3000"+req.url
      );
;
    // console.log("URL user: "+url.searchParams.get('user'));
    // console.log("URL device type: "+url.searchParams.get('deviceType'));
    // console.log("URL device version: "+url.searchParams.get('deviceVersion'));
    // console.log("URL country: "+url.searchParams.get('country'));
    // console.log("URL latitude: "+url.searchParams.get('latitude'));
    // console.log("URL longitude: "+url.searchParams.get('longitude'));
    // console.log("URL timestamp: "+url.searchParams.get('timestamp'));


    var urlS3 = url.searchParams.get('user') + "_" + url.searchParams.get('deviceType') + "_" 
    + url.searchParams.get('deviceVersion') + "_" + url.searchParams.get('country') + "_"
    + url.searchParams.get('latitude') + "_" + url.searchParams.get('longitude') + "_"
    + url.searchParams.get('exception') + "_" +url.searchParams.get('timestamp') + ".json" 

    console.log("url to send to s3 bucket: "+urlS3);

    var formattedURL = urlS3.replace("~", " ");

    console.log("Formatted url: "+formattedURL);


    const myObject = await getObject('downtime-json', formattedURL);

    var jsonData = JSON.parse(myObject);

    console.log("JSON file contents: "+myObject);

    const s3SplitFileObject = new Object();
    s3SplitFileObject.activity = jsonData.activity
    s3SplitFileObject.currentMemoryUsage = jsonData.currentMemoryUsage
    s3SplitFileObject.currentNetworkBandwidth = jsonData.currentNetworkBandwidth
    s3SplitFileObject.currentNetworkType = jsonData.currentNetworkType
    s3SplitFileObject.currentStorageAvailable = jsonData.currentStorageAvailable
    s3SplitFileObject.error = jsonData.error
    s3SplitFileObject.errorCount = jsonData.errorCount
    s3SplitFileObject.modelName = jsonData.modelName
    s3SplitFileObject.osName = jsonData.osName
    s3SplitFileObject.osVersion = jsonData.osVersion
    s3SplitFileObject.ramTotal = jsonData.ramTotal
    s3SplitFileObject.storageTotal = jsonData.storageTotal

    parsedS3ArrayData.push(s3SplitFileObject);

    console.log("Activity: "+ parsedS3ArrayData)



    res.render("users", { s3data: JSON.stringify(parsedS3ArrayData) });
});




async function getObject(bucket, objectKey) {
    try {
        const params = {
            Bucket: bucket,
            Key: objectKey
        }

        const data = await s3.getObject(params).promise();

        //console.log("response from s3: " + data.Body.toString('utf-8'))

        parseS3JSON(data.Body.toString('utf-8'))

        return data.Body.toString('utf-8');
    } catch (e) {
        throw new Error(`Could not retrieve file from S3: ${e.message}`)
    }
}

function parseS3JSON(s3Object) {
    const obj = JSON.parse(s3Object);
    //console.log("Parsed json: " + obj.testfield)



    const s3ParseObjectData = new Object();
    s3ParseObjectData.userId = obj.testfield;
    s3ParseObjectData.deviceType = "Android";
    s3ParseObjectData.version = "9";
    s3ParseObjectData.latitude = "-231321";
    s3ParseObjectData.longitude = "677567";
    s3ParseObjectData.timestamp = "34324234234";

    parsedS3Array.push(s3ParseObjectData)
    //console.log("array size: " + parsedS3Array.length)

    //console.log("Contents: " + JSON.stringify(parsedS3Array))
}



module.exports = router;