var express = require("express");
const AWS = require('aws-sdk');
const json2html = require('node-json2html');
const { WellArchitected } = require("aws-sdk");
const bucket = "downtime-json";
var responseFromS3Bucket = "";

let parsedS3Array = [];

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
            response = response.replace(".json", "");
            var splitResponse = response.split('_');

            const s3SplitFileObject = new Object();
            s3SplitFileObject.userId = splitResponse[0];
            s3SplitFileObject.deviceType = splitResponse[1];
            s3SplitFileObject.deviceVersion = splitResponse[2];
            s3SplitFileObject.latitude = splitResponse[3];
            s3SplitFileObject.longitude = splitResponse[4];
            s3SplitFileObject.timestamp = splitResponse[5];


            parsedS3Array.push(s3SplitFileObject)

        }

        res.render("index", { s3data: JSON.stringify(parsedS3Array) });
    });

    //console.log("Response is: " + responseFromS3Bucket);




    //working 

    //const myObject = await getObject('downtime-json', 'usr109_android_9_480_456_1669911289867.json');
    //res.render("index", {s3data:myObject, device:"Android"})

});

router.get("/users", (req, res, next) => {
    res.render("users");
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