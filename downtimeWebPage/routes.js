var express = require("express");
const AWS = require('aws-sdk');
const bucket = "downtime-json";
var responseFromS3Bucket = "";

let s3 = new AWS.S3({
    region: 'us-east-1',
    accessKeyId: 'AKIA2VFA52HCFF2DJBEA',
    secretAccessKey: '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'
});


var router = express.Router();

router.get("/", async function (req, res, next) {
    // console.log("Index page")
    // let s3Response;

    // var params = {
    //     Bucket: "downtime-json", 
    //     MaxKeys: 10000
    //    };

    //    s3.listObjects(params, function(err, data) {
    //      if (err) console.log(err, err.stack); // an error occurred
    //      else     console.log(data); 
    //      res.render("index", {s3data:JSON.stringify(data)})
    //    });

    //    console.log("Response is: "+responseFromS3Bucket);

    //working 
    const myObject = await getObject('downtime-json', 'android-ireland-1669844633893.json');
    res.render("index", {s3data:myObject, device:"Android"})

});

async function getObject(bucket, objectKey) {
    try {
        const params = {
            Bucket: bucket,
            Key: objectKey
        }

        const data = await s3.getObject(params).promise();

        console.log("response from s3: " + data.Body.toString('utf-8'))

        return data.Body.toString('utf-8');
    } catch (e) {
        throw new Error(`Could not retrieve file from S3: ${e.message}`)
    }
}


module.exports = router;