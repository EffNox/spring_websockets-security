package nixroyal.clientesappback.config.auth;

public class JwtConfig {

    public static final String TOKEN_KEY_SECRET="@ft-nixroyal";
    public static final Integer TOKEN_EXP=3600*4;
    public static final Integer TOKEN_REFRESH=3600*4;
    
    public static final String RSA_PRIVATE="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
    		"MIIEowIBAAKCAQEAvTAzccuVtKNplqzglEYPZ1aUa0cxCH0sMqL7Pz7sBuGdXdPW\r\n" + 
    		"Xu3op3zZsi0LC0IbgxVN9mAF7P8nvsfc5zPI2qUbIM8criglW6L0DIsN+4JQ581R\r\n" + 
    		"bl5QSmA1g7yekbVi1M0lK8uXHXX4Hqb16/fqrO/7JWwjB5qgieJ4+5DosuSU5/g8\r\n" + 
    		"YFeM5dS+1Gy0mT69T1MSJYTpGc4msOkfPGawZbiJZdXO9Np4MtK8DW26WaxZLsOE\r\n" + 
    		"BIMmSoxOO2O/bunsDf8wNmNqQNZW5YTaCeqQSYuqR5aTB7/KjY5I1Rvhkl5UiDRh\r\n" + 
    		"qG4cqur49NVUd3uBfpmg+fKaIZMQLGlVRk0OnwIDAQABAoIBAQCnXJSl/muZOVaX\r\n" + 
    		"LKgfgzrtN/JkVL5TvCf1Q6jv8kHqdHw6HMRvhhdi01fh/2bdtsWP2LD3W0ritXX7\r\n" + 
    		"l+MbxPvSMG0nQREmH+2seyNaVUkxTb800LVQ5a4xnZq5x4qfa87aYgCYFarBLTMq\r\n" + 
    		"Kpky/kZkZofOOzNh7rBGSkqsgSzZ00ShADH57zyrptuUuCfDoZLWYUN4qqMKLJN1\r\n" + 
    		"jPKMxsQ5h9yaOr0c3GKqGCj0TRoKAW479Rxvy2HfFtHxWSo8/nvK6ZkBOcXOAH7i\r\n" + 
    		"AEV6iKRPy7HUM7idkrsA14w/1Gsov+Bx9E9LAEPh8XG1RlRt2+2HLvNq0DJI4Hek\r\n" + 
    		"rv48Sc7BAoGBAPf5OzyJORsqW2k9Puy/ia5dj4SFU0EebwK2u/0osrVofArEHfwh\r\n" + 
    		"9WoiyFpEK0PbIiW6WX+pIBXc3Sf7HB7VD92DSYLQXqXAFz0sqTioMSZQStajRj8v\r\n" + 
    		"iXjXn2E635QzxrP14wxK1/5F4mW6GTdyLZBh2hAEkDnKyC+mkIGjCn5jAoGBAMNP\r\n" + 
    		"3FHI6bXTerckbUZwOJR5E2EkorcLYCPcpJD9XzIzaCEU/H+HgTGAFDBYyyR5Qtfw\r\n" + 
    		"det3b4/VrYUZDixRwBboXh/x5KlWybYzXl7RLs3M0Hx11kItJ2jVLz6KOvtkjH6d\r\n" + 
    		"vfPEB966tVVYg3rcU3AFaf9pcjb4AOL5DDYuDTWVAoGAE9SZmSC8MWD0yHi+ZH54\r\n" + 
    		"yznuNUHYQ5fkdsoYRImRqRI/ATln0HcCwkcf0KAs+ZM3qt65M41Z5IWyJfVd7VVK\r\n" + 
    		"nINYSJ7TQtwWv6ynp8FDlPXM1ldeUcVCcCiGNWdY4+g0FsYuLxh5J3t9PHdidt2p\r\n" + 
    		"qSc6IhfLVOPTmIPwgH2CZFMCgYB8Bq2g3dXTmfzWafJehYrYdd5hlNMjK70caNO0\r\n" + 
    		"IsGd7lkUkPAax+C5n1yyquAtRDe3SsKHCWw1yXoMCDIg4LT7lwmWfZQ4GJ207v/h\r\n" + 
    		"dDKli4dlUasCkQvynVYH/92eoGY7TU4/it94h9X44WF01o7Blx6Ni9d0op5lg8NG\r\n" + 
    		"cI4A1QKBgDWJTJ4S86sUhdnbKDeyJfNQOof6DjIY5I3aDngK/PkCgJ4Whw+nTp5l\r\n" + 
    		"0vQmqlS3EaasadPuquotPsCXHpUKkWWfh3mttLkKxptUUwDsYs6cYJs5XOzc7aD7\r\n" + 
    		"S7mv+yjj1wLv/1VXA3++SpelSFJ3QN7p8RRcFj3c1kOrpYScOfgD\r\n" + 
    		"-----END RSA PRIVATE KEY-----";
    
    public static final String RSA_PUBLIC="-----BEGIN PUBLIC KEY-----\r\n" + 
    		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvTAzccuVtKNplqzglEYP\r\n" + 
    		"Z1aUa0cxCH0sMqL7Pz7sBuGdXdPWXu3op3zZsi0LC0IbgxVN9mAF7P8nvsfc5zPI\r\n" + 
    		"2qUbIM8criglW6L0DIsN+4JQ581Rbl5QSmA1g7yekbVi1M0lK8uXHXX4Hqb16/fq\r\n" + 
    		"rO/7JWwjB5qgieJ4+5DosuSU5/g8YFeM5dS+1Gy0mT69T1MSJYTpGc4msOkfPGaw\r\n" + 
    		"ZbiJZdXO9Np4MtK8DW26WaxZLsOEBIMmSoxOO2O/bunsDf8wNmNqQNZW5YTaCeqQ\r\n" + 
    		"SYuqR5aTB7/KjY5I1Rvhkl5UiDRhqG4cqur49NVUd3uBfpmg+fKaIZMQLGlVRk0O\r\n" + 
    		"nwIDAQAB\r\n" + 
    		"-----END PUBLIC KEY-----";
}
