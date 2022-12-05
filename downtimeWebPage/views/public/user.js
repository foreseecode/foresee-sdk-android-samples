async function getObject(bucket, objectKey) {
    try {
      const params = {
        Bucket: bucket,
        Key: objectKey
      }

      const data = await s3.getObject(params).promise();

      console.log("response from s3: " + data.Body.toString('utf-8'))

      parseS3JSON(data.Body.toString('utf-8'))

      return data.Body.toString('utf-8');
    } catch (e) {
      throw new Error(`Could not retrieve file from S3: ${e.message}`)
    }
  }

