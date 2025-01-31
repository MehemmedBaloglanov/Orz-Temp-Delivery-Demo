/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.intellibucket.kafka.order.avro.model.company;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CompletedAvroProducts extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5116467698150814051L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CompletedAvroProducts\",\"namespace\":\"com.intellibucket.kafka.order.avro.model.company\",\"fields\":[{\"name\":\"productId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"companyId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"subTotal\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<CompletedAvroProducts> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CompletedAvroProducts> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CompletedAvroProducts> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CompletedAvroProducts> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CompletedAvroProducts> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CompletedAvroProducts to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CompletedAvroProducts from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CompletedAvroProducts instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CompletedAvroProducts fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID productId;
  private java.util.UUID companyId;
  private java.math.BigDecimal price;
  private java.math.BigDecimal subTotal;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CompletedAvroProducts() {}

  /**
   * All-args constructor.
   * @param productId The new value for productId
   * @param companyId The new value for companyId
   * @param price The new value for price
   * @param subTotal The new value for subTotal
   */
  public CompletedAvroProducts(java.util.UUID productId, java.util.UUID companyId, java.math.BigDecimal price, java.math.BigDecimal subTotal) {
    this.productId = productId;
    this.companyId = companyId;
    this.price = price;
    this.subTotal = subTotal;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return productId;
    case 1: return companyId;
    case 2: return price;
    case 3: return subTotal;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.DecimalConversion(),
      new org.apache.avro.Conversions.DecimalConversion(),
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: productId = (java.util.UUID)value$; break;
    case 1: companyId = (java.util.UUID)value$; break;
    case 2: price = (java.math.BigDecimal)value$; break;
    case 3: subTotal = (java.math.BigDecimal)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'productId' field.
   * @return The value of the 'productId' field.
   */
  public java.util.UUID getProductId() {
    return productId;
  }


  /**
   * Sets the value of the 'productId' field.
   * @param value the value to set.
   */
  public void setProductId(java.util.UUID value) {
    this.productId = value;
  }

  /**
   * Gets the value of the 'companyId' field.
   * @return The value of the 'companyId' field.
   */
  public java.util.UUID getCompanyId() {
    return companyId;
  }


  /**
   * Sets the value of the 'companyId' field.
   * @param value the value to set.
   */
  public void setCompanyId(java.util.UUID value) {
    this.companyId = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public java.math.BigDecimal getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(java.math.BigDecimal value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'subTotal' field.
   * @return The value of the 'subTotal' field.
   */
  public java.math.BigDecimal getSubTotal() {
    return subTotal;
  }


  /**
   * Sets the value of the 'subTotal' field.
   * @param value the value to set.
   */
  public void setSubTotal(java.math.BigDecimal value) {
    this.subTotal = value;
  }

  /**
   * Creates a new CompletedAvroProducts RecordBuilder.
   * @return A new CompletedAvroProducts RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder newBuilder() {
    return new com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder();
  }

  /**
   * Creates a new CompletedAvroProducts RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CompletedAvroProducts RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder newBuilder(com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder other) {
    if (other == null) {
      return new com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder();
    } else {
      return new com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder(other);
    }
  }

  /**
   * Creates a new CompletedAvroProducts RecordBuilder by copying an existing CompletedAvroProducts instance.
   * @param other The existing instance to copy.
   * @return A new CompletedAvroProducts RecordBuilder
   */
  public static com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder newBuilder(com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts other) {
    if (other == null) {
      return new com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder();
    } else {
      return new com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder(other);
    }
  }

  /**
   * RecordBuilder for CompletedAvroProducts instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CompletedAvroProducts>
    implements org.apache.avro.data.RecordBuilder<CompletedAvroProducts> {

    private java.util.UUID productId;
    private java.util.UUID companyId;
    private java.math.BigDecimal price;
    private java.math.BigDecimal subTotal;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.companyId)) {
        this.companyId = data().deepCopy(fields()[1].schema(), other.companyId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.price)) {
        this.price = data().deepCopy(fields()[2].schema(), other.price);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.subTotal)) {
        this.subTotal = data().deepCopy(fields()[3].schema(), other.subTotal);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing CompletedAvroProducts instance
     * @param other The existing instance to copy.
     */
    private Builder(com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.companyId)) {
        this.companyId = data().deepCopy(fields()[1].schema(), other.companyId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.price)) {
        this.price = data().deepCopy(fields()[2].schema(), other.price);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.subTotal)) {
        this.subTotal = data().deepCopy(fields()[3].schema(), other.subTotal);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'productId' field.
      * @return The value.
      */
    public java.util.UUID getProductId() {
      return productId;
    }


    /**
      * Sets the value of the 'productId' field.
      * @param value The value of 'productId'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder setProductId(java.util.UUID value) {
      validate(fields()[0], value);
      this.productId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'productId' field has been set.
      * @return True if the 'productId' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'productId' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder clearProductId() {
      productId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'companyId' field.
      * @return The value.
      */
    public java.util.UUID getCompanyId() {
      return companyId;
    }


    /**
      * Sets the value of the 'companyId' field.
      * @param value The value of 'companyId'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder setCompanyId(java.util.UUID value) {
      validate(fields()[1], value);
      this.companyId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'companyId' field has been set.
      * @return True if the 'companyId' field has been set, false otherwise.
      */
    public boolean hasCompanyId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'companyId' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder clearCompanyId() {
      companyId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public java.math.BigDecimal getPrice() {
      return price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder setPrice(java.math.BigDecimal value) {
      validate(fields()[2], value);
      this.price = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder clearPrice() {
      price = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'subTotal' field.
      * @return The value.
      */
    public java.math.BigDecimal getSubTotal() {
      return subTotal;
    }


    /**
      * Sets the value of the 'subTotal' field.
      * @param value The value of 'subTotal'.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder setSubTotal(java.math.BigDecimal value) {
      validate(fields()[3], value);
      this.subTotal = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'subTotal' field has been set.
      * @return True if the 'subTotal' field has been set, false otherwise.
      */
    public boolean hasSubTotal() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'subTotal' field.
      * @return This builder.
      */
    public com.intellibucket.kafka.order.avro.model.company.CompletedAvroProducts.Builder clearSubTotal() {
      subTotal = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CompletedAvroProducts build() {
      try {
        CompletedAvroProducts record = new CompletedAvroProducts();
        record.productId = fieldSetFlags()[0] ? this.productId : (java.util.UUID) defaultValue(fields()[0]);
        record.companyId = fieldSetFlags()[1] ? this.companyId : (java.util.UUID) defaultValue(fields()[1]);
        record.price = fieldSetFlags()[2] ? this.price : (java.math.BigDecimal) defaultValue(fields()[2]);
        record.subTotal = fieldSetFlags()[3] ? this.subTotal : (java.math.BigDecimal) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CompletedAvroProducts>
    WRITER$ = (org.apache.avro.io.DatumWriter<CompletedAvroProducts>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CompletedAvroProducts>
    READER$ = (org.apache.avro.io.DatumReader<CompletedAvroProducts>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










