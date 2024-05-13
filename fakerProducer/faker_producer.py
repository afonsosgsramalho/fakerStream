from faker import Faker
import time

fake = Faker()

# Generate fake data
def generate_fake_data():
    fake_record = {
        'name': fake.first_name_nonbinary(),
        'address': fake.address(),
        'email': fake.email(),
        'phone_number': fake.phone_number(),
        'job': fake.job(),
        'company': fake.company(),
        'birthdate': fake.date_of_birth(minimum_age=18, maximum_age=90).strftime('%Y-%m-%d'),
        'credit_card_number': fake.credit_card_number(),
        'credit_card_expiry': fake.credit_card_expire(),
        'credit_card_provider': fake.credit_card_provider(),
        'username': fake.user_name(),
        'password': fake.password(),
        'profile_pic': fake.image_url(),
    }

    return fake_record


if __name__ == "__main__":
    while True:
        fake_record = generate_fake_data()
        time.sleep(0.3)
        print(fake_record)

